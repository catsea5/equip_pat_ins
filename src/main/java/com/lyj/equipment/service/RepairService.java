package com.lyj.equipment.service;

import com.lyj.equipment.vo.repair.myRepairVo;

import java.util.List;

public interface RepairService {
    void addRepair(Integer uid, Integer eid, String commit);

    List<myRepairVo> getMyRepair(String token);
}
