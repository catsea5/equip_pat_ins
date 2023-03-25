package com.lyj.equipment.controller;

import com.lyj.equipment.service.RepairService;
import com.lyj.equipment.util.Result;
import com.lyj.equipment.vo.repair.myRepairVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RepairController {
    @Autowired
    private RepairService repairService;

    //选择人员进行维修
    @ApiOperation(value="设备维修")
    @GetMapping("repair/{uid}/{eid}")
    public Result repair(@PathVariable Integer uid, @PathVariable Integer eid, @RequestParam("commit") String commit){
        repairService.addRepair(uid,eid,commit);
        return Result.ok();
    }
    //巡检人员获得自己的维修信息
    @ApiOperation(value="user设备维修")
    @GetMapping("myrepair/{token}")
    public Result myrepair(@PathVariable String token){
        List<myRepairVo> list = repairService.getMyRepair(token);
        return Result.ok(list);
    }
}
