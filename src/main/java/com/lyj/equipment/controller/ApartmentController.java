package com.lyj.equipment.controller;

import com.lyj.equipment.entity.Apartment;
import com.lyj.equipment.service.ApartmentService;
import com.lyj.equipment.util.Result;
import com.lyj.equipment.vo.apartment.ApartmentUpdateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("apartment")
@Api(tags={"房间模块api"})
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;
    //获取所有房间号
    @ApiOperation(value="获取房间信息")
    @GetMapping("anumberlist")
    public Result findAnumber(){
        List<Apartment> list = apartmentService.findAnumber();
        return Result.ok(list);
    }
    //添加房间
    @ApiOperation(value="传入房间号添加房间")
    @GetMapping("add/{anumber}")
    public Result addAprtment(@PathVariable String anumber){
        apartmentService.addApartment(anumber);
        return Result.ok();
    }
    //修改房间
    @ApiOperation(value="修改房间")
    @PostMapping("update")
    public Result updateApartment(@RequestBody ApartmentUpdateVo apartmentUpdateVo) {
        apartmentService.updateAprtment(apartmentUpdateVo);
        return Result.ok();
    }
    //获取没有被分配的房间号
    @ApiOperation(value="获取没有被分配的房间号，通过pid查询已经分配的房间id")
    @GetMapping("list/{pid}")
    public Result list(@PathVariable Integer pid){
        List<Apartment> list =apartmentService.findByPid(pid);
        return Result.ok(list);
    }
}
