package com.lyj.equipment.service.impl;

import com.lyj.equipment.entity.User;
import com.lyj.equipment.exception.EquipException;
import com.lyj.equipment.mapper.LoginMapper;
import com.lyj.equipment.service.LoginService;
import com.lyj.equipment.util.JwtHelper;
import com.lyj.equipment.util.ResultCodeEnum;
import com.lyj.equipment.vo.login.LoginVo;
import com.lyj.equipment.vo.login.LogonVo;
import com.lyj.equipment.vo.login.RePasswordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.alibaba.excel.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl  implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String getCode() {
        int random = (int)(Math.random()*1000000);
        String code = String.format("%06d", random);
        return code;
    }

    @Override
    public void sendEmail(String email, String code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("设备巡检系统");
        simpleMailMessage.setText("尊敬的："+email+"，您的操作校验验证码为："+code+"，有效期5分钟");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("2630246701@qq.com");
        javaMailSender.send(simpleMailMessage);
    }
    //注册
    @Override
    public void logon(LogonVo logonVo) {
        //获邮箱和验证码和密码
        String email = logonVo.getEmail();
        String code = logonVo.getCode();
        String password = logonVo.getPassword();
//        //判断邮箱和验证码是否为空
//        if(StringUtils.isEmpty(email)||StringUtils.isEmpty(code)){
//            throw  new EquipException(ResultCodeEnum.PARAM_ERROR);
//        }
        //TODO 判断验证码是否一致
        String emailCode = (String)redisTemplate.opsForValue().get(email);
        if(!code.equals(emailCode)){
            throw  new EquipException(ResultCodeEnum.CODE_ERROR);
        }
        //判断是否为第一次登录 是-》注册信息 否-》提示已经注册
        Integer exist = loginMapper.isExist(email);
        //如果不存在则插入，并设置状态信息为2
        if(exist==0){
            String name = logonVo.getName();
            User user = new User();
            user.setUemail(email);
            user.setUname(name);
            user.setUpassword(password);
            user.setPermission(0);
            user.setUstate(2);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            loginMapper.insert(user);

            redisTemplate.delete(email);

        }else{
            throw  new EquipException(ResultCodeEnum.LOGIN_MOBLE_EXIST);
        }
    }
    //登录
    @Override
    public Map<String, Object> login(LoginVo loginVo) {
        //获邮箱和密码
        String email = loginVo.getEmail();
        String password = loginVo.getPassword();
        Integer exist = loginMapper.isExist(email);
        Map<String,Object> info = new HashMap<>();
        if(exist==0){
            throw  new EquipException(ResultCodeEnum.LOGIN_MOBLE_ERROR);
        }else{
            User user = loginMapper.selectByEmail(email);
            if(user.getUpassword().equals(password)){
                if(user.getUstate() == 2){
                    throw  new EquipException(ResultCodeEnum.LOGIN_MOBLE_APPLY);
                }
                info.put("name",user.getUname());
                //返回token
                String token = JwtHelper.createToken(user.getUid(), user.getUname(), email);
                info.put("token",token);
            }else {
                throw  new EquipException(ResultCodeEnum.PASSWORD_ERROR);
            }
        }
        return info;
    }

    @Override
    public void change(RePasswordVo rePasswordVo) {
        //获邮箱和验证码和密码
        String email = rePasswordVo.getEmail();
        String code = rePasswordVo.getCode();
        //判断邮箱和验证码是否为空
        if(StringUtils.isEmpty(email)||StringUtils.isEmpty(code)){
            throw  new EquipException(ResultCodeEnum.PARAM_ERROR);
        }
        //判断用户是否存在
        Integer exist = loginMapper.isExist(email);
        if(exist == 0) {
            throw  new EquipException(ResultCodeEnum.LOGIN_MOBLE_ERROR);
        }
        //判断用户是否审核中
        User user = loginMapper.selectByEmail(email);
        if(user.getUstate() == 2) {
            throw  new EquipException(ResultCodeEnum.LOGIN_MOBLE_APPLY);
        }
        //TODO 判断验证码是否一致
        String emailCode = (String)redisTemplate.opsForValue().get(email);
        if(!code.equals(emailCode)){
            throw  new EquipException(ResultCodeEnum.CODE_ERROR);
        }
        loginMapper.update(rePasswordVo);
    }

    @Override
    public String adminLogin(LoginVo loginVo) {
        //获邮箱和密码
        String email = loginVo.getEmail();
        String password = loginVo.getPassword();
        Integer exist = loginMapper.isExist(email);
        String token = null;
        if(exist==0){
            throw  new EquipException(ResultCodeEnum.LOGIN_MOBLE_ERROR);
        }else{
            User user = loginMapper.selectByEmail(email);
            if(user.getUpassword().equals(password)){
                if(user.getPermission() == 0){
                    throw  new EquipException(ResultCodeEnum.PERMISSION);
                }
                //返回token
                token = JwtHelper.createToken(user.getUid(), user.getUname(), email);
            }else {
                throw  new EquipException(ResultCodeEnum.PASSWORD_ERROR);
            }
        }
        return token;
    }
}
