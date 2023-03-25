package com.lyj.equipment.controller;

import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.User;
import com.lyj.equipment.service.UserService;
import com.lyj.equipment.util.Result;
import com.lyj.equipment.vo.user.PageVo;
import com.lyj.equipment.vo.user.UserListVo;
import com.lyj.equipment.vo.user.UserUpdateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags={"用户模块api"})
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="展示所有用户（状态 1和状态 0）")
    @PostMapping("list")
    public Result list(@RequestBody PageVo pageVo){
        PageInfo<User> pageInfo = userService.findByPage(pageVo);
        return Result.ok(pageInfo);
    }

    //查询 通过 id
    @ApiOperation(value="查询用户（用户id）")
    @GetMapping("query/{id}")
    public Result queryByid(@PathVariable("id") Integer id){
        User user = userService.queryById(id);
        return Result.ok(user);
    }
    //更改用户状态（包括删除、恢复、申请是否通过）
    @ApiOperation(value="删除用户（用户id）")
    @GetMapping("change/{id}/{state}")
    public Result change(@PathVariable("id") Integer id, @PathVariable Integer state){
        userService.change(id,state);
        return Result.ok();
    }
    //修改
    @ApiOperation(value="修改用户信息")
    @PostMapping("update")
    public Result update(@RequestBody UserUpdateVo userUpdateVo){
        userService.update(userUpdateVo);
        return Result.ok();
    }
//    //展示申请中的用户
    @ApiOperation(value="展示正在申请中的账号信息")
    @PostMapping("applying")
    public Result apply(@RequestBody PageVo pageVo){
        PageInfo<User> pageInfo = userService.findApplyByPage(pageVo);
        return Result.ok(pageInfo);
    }
    //获取用户状态正常的id和用户名
    @GetMapping("listvo")
    public Result listvo(){
        List<UserListVo> list = userService.findIdAndName();
        return Result.ok(list);
    }
}
