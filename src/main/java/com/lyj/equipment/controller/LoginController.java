package com.lyj.equipment.controller;

import com.lyj.equipment.service.LoginService;
import com.lyj.equipment.util.JwtHelper;
import com.lyj.equipment.util.Result;
import com.lyj.equipment.vo.login.LoginVo;
import com.lyj.equipment.vo.login.LogonVo;
import com.lyj.equipment.vo.login.RePasswordVo;
import com.lyj.equipment.vo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(tags={"登录模块api"})
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisTemplate redisTemplate;

    //登录
    @ApiOperation(value="登录")
    @PostMapping("login")
    public Result Login(@RequestBody LoginVo loginVo){
        Map<String,Object> userinfo = loginService.login(loginVo);
        return Result.ok(userinfo);
    }
    //发送邮箱验证码
    @ApiOperation(value="获取邮箱验证码")
    @GetMapping("sendEmail/{email}")
    public Result sendCode(@PathVariable String email) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 邮箱号  value 验证码
        String code = (String) redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = loginService.getCode();
        loginService.sendEmail(email,code);
        redisTemplate.opsForValue().set(email,code,5, TimeUnit.MINUTES);
        return Result.ok();
    }
    //注册
    @ApiOperation(value="注册")
    @PostMapping("logon")
    public Result logon(@RequestBody LogonVo logonVo){
        loginService.logon(logonVo);
        return Result.ok();
    }
    //忘记密码 输入邮箱 新密码和邮箱验证码进行密码修改
    @ApiOperation(value="修改密码")
    @PostMapping("change")
    public Result changePassword(@RequestBody RePasswordVo rePasswordVo){
         loginService.change(rePasswordVo);
        return Result.ok();
    }

    //管理员登录
    @ApiOperation(value="管理员登录")
    @PostMapping("admin/login")
    public Result adminLogin(@RequestBody LoginVo loginVo){
        String token = loginService.adminLogin(loginVo);
        return Result.ok(token);
    }
    //管理员信息
    @ApiOperation(value="管理员信息获取")
    @GetMapping("admin/info")
    public Result getInfo(String token){
        return Result.ok(JwtHelper.getUserName(token));
    }
}
