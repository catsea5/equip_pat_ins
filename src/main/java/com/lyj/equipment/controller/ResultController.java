package com.lyj.equipment.controller;

import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.User;
import com.lyj.equipment.service.ResultService;
import com.lyj.equipment.util.Result;
import com.lyj.equipment.vo.result.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("result")
public class ResultController {
    @Autowired
    private ResultService resultService;

    //当用户上传时会去更新用户的巡检状态，比如巡检房间中的设备，就改变巡检状态为进行中，如果该房间所以设备都巡检完成后，改变巡检状态为已巡检
    @PostMapping("upload")
    public Result upload(@RequestBody UploadVo uploadVo){
        resultService.upload(uploadVo);
        return Result.ok();
    }

    //分页获取结果列表
    @PostMapping("list")
    public Result list(@RequestBody ResultPageVo resultPageVo){
        PageInfo<ResultWebListVo> pageInfo = resultService.getResultInfoList(resultPageVo);
        return Result.ok(pageInfo);
    }
    //导出数据字典接口
    @GetMapping("exportData")
    public void export(HttpServletResponse response) {
        resultService.exportData(response);
    }

    //用户查询巡检历史接口
    @PostMapping("myhistory")
    public Result myHistory(@RequestBody UserHistoryVo userHistoryVo){
        List<ResultWebListVo> list = resultService.getMyHistory(userHistoryVo);
        return Result.ok(list);
    }
}
