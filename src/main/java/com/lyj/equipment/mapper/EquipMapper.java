package com.lyj.equipment.mapper;

import com.lyj.equipment.entity.Equip;
import com.lyj.equipment.entity.Repair;
import com.lyj.equipment.vo.equip.EquipPageVo;
import com.lyj.equipment.vo.equip.EquipUpdateVo;
import com.lyj.equipment.vo.repair.myRepairVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface EquipMapper {
    List<Equip> findApplyByKeyword(EquipPageVo equipPageVo);

    Equip findById(String id);

    void change(String id, String state);

    List<String> findType();

    void add(Equip equip);

    Integer maxId();

    void addEA(@Param("aid") Integer aid, @Param("id") Integer id);

    void update(Equip equipUpdateVo);

    void updateEA(EquipUpdateVo equipUpdateVo);

    void changeState(@Param("eid") Integer eid,@Param("state") Integer state);

    Integer getStateByEid(Integer eid);
}
