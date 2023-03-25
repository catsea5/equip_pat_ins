package com.lyj.equipment.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.Result;
import com.lyj.equipment.entity.User;
import com.lyj.equipment.mapper.*;
import com.lyj.equipment.service.ResultService;
import com.lyj.equipment.util.JwtHelper;
import com.lyj.equipment.vo.result.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceIpml implements ResultService {
    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private EquipMapper equipMapper;

    @Autowired
    private RepairMapper repairMapper;

    @Override
    public void upload(UploadVo uploadVo) {
        Result result = new Result();
        result.setPid(uploadVo.getPid());
        result.setUid(JwtHelper.getUserId(uploadVo.getToken()));
        result.setEid(uploadVo.getEid());
        result.setState(uploadVo.getState());
        result.setUploadTime(LocalDateTime.now());
        result.setCommit(uploadVo.getCommit());
        resultMapper.insert(result);
        //改变设备的当前状态
        equipMapper.changeState(result.getEid(),result.getState());
        if(result.getPid() == 11) {
            //如果pid等于11表示这次上传是维修上传
            repairMapper.changeRepairState(result.getEid(),result.getUid(),LocalDateTime.now());
        }else {
            // 根据 eid 获取到 aid
            // 根据 aid 获取到所有 aid 再与 result 表中进行对比 找出没有在 result 表中的 aid 的数量
            int equipNum = resultMapper.getEquipConut(result.getEid());
            // 根据 aid 的数量修改 schedule 中对应的状态
            if(equipNum == 0) { //如果为0就表示已完成
                scheduleMapper.changeStateToOneBy(result.getPid(),result.getEid());
            }
        }
    }

    @Override
    @Cacheable(value = "result",keyGenerator = "keyGenerator")
    public PageInfo<ResultWebListVo> getResultInfoList(ResultPageVo resultPageVo) {
        //创建分页
        Page<Object> objects = PageHelper.startPage(resultPageVo.getPage(), resultPageVo.getLimit());
        List<ResultWebListVo> list;
        list = resultMapper.getPageList(resultPageVo);
        PageInfo<ResultWebListVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    //需要进行修改，专门为导出写一个sql查询语句和一个导出实体类
    @Override
    public void exportData(HttpServletResponse response) {
        //设置下载信息
        String fileName = "result.xlsx";
        response.setContentType("application/ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName );

        //查询数据库
        List<ResultExportVo> resultList = resultMapper.export();
        //调用方法进行写操作
        try {
            EasyExcel.write(response.getOutputStream(),ResultExportVo.class).sheet("result").doWrite(resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    @Cacheable(value = "myhistoryresult",keyGenerator = "keyGenerator")
    public List<ResultWebListVo> getMyHistory(UserHistoryVo userHistoryVo) {
        Integer uid = JwtHelper.getUserId(userHistoryVo.getToken());
        return resultMapper.getMyHistory(uid);
    }
}
