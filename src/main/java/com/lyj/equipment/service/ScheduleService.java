package com.lyj.equipment.service;

import com.lyj.equipment.vo.schedule.AddScheduleVo;
import com.lyj.equipment.vo.schedule.ApartmentEquipListVo;
import com.lyj.equipment.vo.schedule.ScheduleListVo;
import com.lyj.equipment.vo.schedule.WxInfoVo;

import java.util.List;

public interface ScheduleService {
    List<ScheduleListVo> findScheduleByPid(Integer id);

    void deleteByAidAndPid(Integer aid, Integer pid);

    void addSchedule(AddScheduleVo addScheduleVo);

    List<ApartmentEquipListVo> getInfoList(WxInfoVo wxInfoVo);
}
