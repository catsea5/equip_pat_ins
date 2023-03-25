package com.lyj.equipment.service;

import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.Equip;
import com.lyj.equipment.vo.equip.EquipAddVo;
import com.lyj.equipment.vo.equip.EquipPageVo;
import com.lyj.equipment.vo.equip.EquipUpdateVo;
import com.lyj.equipment.vo.repair.myRepairVo;
import com.lyj.equipment.vo.result.UploadVo;

import java.util.List;

public interface EquipService {

    PageInfo<Equip> findByPage(EquipPageVo equipPageVo);

    Equip findById(String id);

    void change(String id, String state);

    List<String> findType();

    void add(EquipAddVo equipAddVo);

    void update(EquipUpdateVo equipUpdateVo);

    Equip scanInfo(UploadVo uploadVo);

}
