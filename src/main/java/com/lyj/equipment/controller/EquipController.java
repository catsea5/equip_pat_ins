package com.lyj.equipment.controller;

import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.Equip;
import com.lyj.equipment.service.EquipService;
import com.lyj.equipment.util.Result;
import com.lyj.equipment.vo.equip.EquipAddVo;
import com.lyj.equipment.vo.equip.EquipPageVo;
import com.lyj.equipment.vo.equip.EquipUpdateVo;
import com.lyj.equipment.vo.result.UploadVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags={"设备模块api"})
@RestController
@RequestMapping("equip")
@CrossOrigin
public class EquipController {
    @Autowired
    private EquipService equipService;
    //添加设备，按照房间号添加
    //查看设备，分页，按照房间号来分类
    @ApiOperation(value="获取设备列表")
    @PostMapping("list")
    public Result list(@RequestBody EquipPageVo equipPageVo){
        PageInfo<Equip> pageInfo = equipService.findByPage(equipPageVo);
        return Result.ok(pageInfo);
    }
    //根据id查询设备信息
    @ApiOperation(value="根据id查询设备信息")
    @GetMapping("query/{id}")
    public Result query(@PathVariable String id) {
        Equip equip = equipService.findById(id);
        return Result.ok(equip);
    }
    //微信小程序扫描获取设备信息，不能获取到未启用的设备信息,不能获取到自己任务以外的设备信息
    @ApiOperation(value="根据用户信息判断并获取相应的设备信息")
    @PostMapping("scan")
    public Result scan(@RequestBody UploadVo uploadVo){
        System.out.println(uploadVo);
        Equip equip = equipService.scanInfo(uploadVo);
        return Result.ok(equip);
    }
    //锁定和解锁设备，并非真正删除，而是将状态改为0
    @ApiOperation(value="锁定和解锁设备")
    @GetMapping("change/{id}/{state}")
    public Result change(@PathVariable String id, @PathVariable String state){
        equipService.change(id,state);
        return Result.ok();
    }
    //查询所有设备类型
    @ApiOperation(value="查询所有设备类型")
    @GetMapping("typelist")
    public Result typeList(){
        List<String> list = equipService.findType();
        return Result.ok(list);
    }
    //添加设备
    @ApiOperation(value="添加设备")
    @PostMapping("add")
    public Result add(@RequestBody EquipAddVo equipAddVo){
        System.out.println(equipAddVo);
        equipService.add(equipAddVo);
        return Result.ok();
    }
    //修改设备信息
    @ApiOperation(value="修改设备信息")
    @PostMapping("update")
    public Result add(@RequestBody EquipUpdateVo equipUpdateVo){
        equipService.update(equipUpdateVo);
        return Result.ok();
    }
}
