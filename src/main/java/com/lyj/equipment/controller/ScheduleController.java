package com.lyj.equipment.controller;

import com.lyj.equipment.entity.Schedule;
import com.lyj.equipment.service.ScheduleService;
import com.lyj.equipment.util.Result;
import com.lyj.equipment.vo.schedule.AddScheduleVo;
import com.lyj.equipment.vo.schedule.ApartmentEquipListVo;
import com.lyj.equipment.vo.schedule.ScheduleListVo;
import com.lyj.equipment.vo.schedule.WxInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("schedule")
@Api(tags={"人员分配表模块api"})
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    //通过巡检计划id来获取巡检安排表
    @ApiOperation(value="通过巡检计划id来获取人员分配列表")
    @GetMapping("list/{id}")
    public Result listByPid(@PathVariable Integer id){
        List<ScheduleListVo> list = scheduleService.findScheduleByPid(id);
        return Result.ok(list);
    }
    //通过aid和pid来进行删除安排
    @ApiOperation(value="通过巡检计划id和房间id来删除人员分配数据")
    @GetMapping("delete/{aid}/{pid}")
    public Result delete(@PathVariable Integer aid, @PathVariable Integer pid){
        System.out.println(aid+"mmm "+pid);
        scheduleService.deleteByAidAndPid(aid,pid);
        return Result.ok();
    }
    //添加安排
    @ApiOperation(value="添加人员分配数据")
    @PostMapping("add")
    public Result add(@RequestBody AddScheduleVo addScheduleVo){
        scheduleService.addSchedule(addScheduleVo);
        return Result.ok();
    }
    //通过用户id和巡检计划id获取该用户需要巡检的房间号以及每个房间包含的设备信息
    @ApiOperation(value="通过用户id和巡检计划id获取该用户需要巡检的房间号以及每个房间包含的设备信息")
    @PostMapping("wxinfolist")
    public Result wxinfolist(@RequestBody WxInfoVo wxInfoVo){
       List<ApartmentEquipListVo> list = scheduleService.getInfoList(wxInfoVo);
        return Result.ok(list);
    }
}
