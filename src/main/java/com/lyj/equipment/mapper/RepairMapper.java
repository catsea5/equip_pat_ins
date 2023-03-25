package com.lyj.equipment.mapper;

import com.lyj.equipment.entity.Repair;
import com.lyj.equipment.vo.email.RepairEmailInfo;
import com.lyj.equipment.vo.repair.myRepairVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface RepairMapper {

    void addRepair(Repair repair);

    List<myRepairVo> getMyRepair(Integer uid);

    Integer isExistByUidAndEid(@Param("uid") Integer uid, @Param("eid") Integer eid);

    void changeRepairState(@Param("eid") Integer eid, @Param("uid") Integer uid,@Param("updateTime") LocalDateTime updateTime);

    RepairEmailInfo sendEmail(@Param("uid") Integer uid, @Param("eid") Integer eid);
}
