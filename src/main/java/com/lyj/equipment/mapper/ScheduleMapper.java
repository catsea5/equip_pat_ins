package com.lyj.equipment.mapper;

import com.lyj.equipment.entity.Equip;
import com.lyj.equipment.vo.email.ScheduleEmailInfo;
import com.lyj.equipment.vo.schedule.AddScheduleVo;
import com.lyj.equipment.vo.schedule.ApartmentEquipListVo;
import com.lyj.equipment.vo.schedule.ScheduleListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ScheduleMapper {
    List<ScheduleListVo> findScheduleByPid(Integer id);

    void deleteByAidAndPid(@Param("aid") Integer aid, @Param("pid") Integer pid);

    void add(AddScheduleVo addScheduleVo);

    List<ApartmentEquipListVo> getInfoList(@Param("pid") Integer pid,@Param("uid") Integer uid);

    List<Equip> getEquipList(@Param("aid") Integer aid, @Param("pid") Integer pid, @Param("uid") Integer uid);

    void changeStateToOneBy(@Param("pid") Integer pid,@Param("eid") Integer eid);

    Integer isExistByUidAndEid(@Param("uid") Integer uid, @Param("eid") Integer eid);

    ScheduleEmailInfo sendEmail(AddScheduleVo addScheduleVo);
}
