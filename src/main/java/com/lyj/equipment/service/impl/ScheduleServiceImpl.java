package com.lyj.equipment.service.impl;

import com.lyj.equipment.mapper.ScheduleMapper;
import com.lyj.equipment.service.ScheduleService;
import com.lyj.equipment.util.JwtHelper;
import com.lyj.equipment.vo.email.ScheduleEmailInfo;
import com.lyj.equipment.vo.schedule.AddScheduleVo;
import com.lyj.equipment.vo.schedule.ApartmentEquipListVo;
import com.lyj.equipment.vo.schedule.ScheduleListVo;
import com.lyj.equipment.vo.schedule.WxInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private JavaMailSender javaMailSender;
    //传入pid来获取Schedulelist
    @Override
    public List<ScheduleListVo> findScheduleByPid(Integer id) {
        return scheduleMapper.findScheduleByPid(id);
    }

    @Override
    public void deleteByAidAndPid(Integer aid, Integer pid) {
        scheduleMapper.deleteByAidAndPid(aid,pid);
    }

    @Override
    public void addSchedule(AddScheduleVo addScheduleVo) {
        scheduleMapper.add(addScheduleVo);
        //发送邮箱短信
        ScheduleEmailInfo scheduleEmailInfo = scheduleMapper.sendEmail(addScheduleVo);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("设备巡检系统");
        simpleMailMessage.setText("尊敬的："+ scheduleEmailInfo.getUname()+"，您有一个巡检任务安排。"+
                "巡检计划:"+scheduleEmailInfo.getPname()+"。"+
                "巡检位置:"+scheduleEmailInfo.getAnumber()+"。"+
                "开始时间:"+scheduleEmailInfo.getStartTime()+"。"+
                "截止时间:"+scheduleEmailInfo.getEndTime()+"。"+
                "备注:"+addScheduleVo.getCommit()+"。");
        simpleMailMessage.setTo(scheduleEmailInfo.getUemail());
        simpleMailMessage.setFrom("2630246701@qq.com");
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public List<ApartmentEquipListVo> getInfoList(WxInfoVo wxInfoVo) {
        //获取用户id和巡检计划id
        Integer pid = wxInfoVo.getPid();
        Integer uid = JwtHelper.getUserId(wxInfoVo.getToken());
        //1.从schedule表中获取对应的房间id
        //2.根据对应的房间id获取到房间信息
        //3.根据房间id获取到设备id 联合结果表排除已经巡检过的设备id
        //4.根据设备id获取到设备信息
        return scheduleMapper.getInfoList(pid,uid);
    }
}
