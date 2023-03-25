package com.lyj.equipment.service.impl;

import com.lyj.equipment.entity.Repair;
import com.lyj.equipment.mapper.RepairMapper;
import com.lyj.equipment.service.RepairService;
import com.lyj.equipment.util.JwtHelper;
import com.lyj.equipment.vo.email.RepairEmailInfo;
import com.lyj.equipment.vo.repair.myRepairVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private RepairMapper repairMapper;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void addRepair(Integer uid, Integer eid, String commit) {
        Repair repair = new Repair();
        repair.setUid(uid);
        repair.setEid(eid);
        repair.setState(1);
        repair.setCreateTime(LocalDateTime.now());
        repair.setUploadTime(LocalDateTime.now());
        repair.setCommit(commit);
        repairMapper.addRepair(repair);
        //发送邮箱短信
        RepairEmailInfo repairEmailInfo = repairMapper.sendEmail(uid,eid);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("设备巡检系统");
        simpleMailMessage.setText("尊敬的："+ repairEmailInfo.getUname()+"，您有一个维修任务安排，" +
                "维修设备:"+ repairEmailInfo.getEname()+",设备位置:"+ repairEmailInfo.getAnumber()+"，详细信息请登录设备巡检小程序进行查阅。");
        simpleMailMessage.setTo(repairEmailInfo.getUemail());
        simpleMailMessage.setFrom("2630246701@qq.com");
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public List<myRepairVo> getMyRepair(String token) {
        //获取用户id
        Integer uid = JwtHelper.getUserId(token);
        return repairMapper.getMyRepair(uid);
    }
}
