package com.lyj.equipment.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.Apartment;
import com.lyj.equipment.entity.Equip;
import com.lyj.equipment.entity.Repair;
import com.lyj.equipment.exception.EquipException;
import com.lyj.equipment.mapper.EquipMapper;
import com.lyj.equipment.mapper.RepairMapper;
import com.lyj.equipment.mapper.ScheduleMapper;
import com.lyj.equipment.service.EquipService;
import com.lyj.equipment.util.JwtHelper;
import com.lyj.equipment.util.ResultCodeEnum;
import com.lyj.equipment.vo.equip.EquipAddVo;
import com.lyj.equipment.vo.equip.EquipPageVo;
import com.lyj.equipment.vo.equip.EquipUpdateVo;
import com.lyj.equipment.vo.repair.myRepairVo;
import com.lyj.equipment.vo.result.UploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipServiceImpl implements EquipService {
    @Autowired
    private EquipMapper equipMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private RepairMapper repairMapper;

    @Override
    public PageInfo<Equip> findByPage(EquipPageVo equipPageVo) {
        //创建分页
        Page<Object> objects = PageHelper.startPage(equipPageVo.getPage(), equipPageVo.getLimit());
        List<Equip> list;
        list = equipMapper.findApplyByKeyword(equipPageVo);
        PageInfo<Equip> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Equip findById(String id) {
        return equipMapper.findById(id);
    }

    @Override
    public void change(String id, String state) {
        equipMapper.change(id,state);
    }

    @Override
    public List<String> findType() {
        return equipMapper.findType();
    }

    @Override
    public void add(EquipAddVo equipAddVo) {
        Equip equip = new Equip();
        equip.setEname(equipAddVo.getName());
        equip.setType(equipAddVo.getType());
        equip.setCreateTime(LocalDateTime.now());
        equip.setUpdateTime(LocalDateTime.now());
        equip.setState(0);
        equip.setAssetCode(equipAddVo.getAssetCode());
        equip.setCommit(equipAddVo.getCommit());
        equipMapper.add(equip);
        Integer id = equipMapper.maxId();
        equipMapper.addEA(equipAddVo.getAid(),id);

    }

    @Override
    public void update(EquipUpdateVo equipUpdateVo) {
        Equip equip = new Equip();
        equip.setEid(equipUpdateVo.getId());
        equip.setEname(equipUpdateVo.getName());
        equip.setType(equipUpdateVo.getType());
        equip.setUpdateTime(LocalDateTime.now());
        equip.setState(equipUpdateVo.getState());
        //修改设备表
        equipMapper.update(equip);
        //修改房间-设备表（按设备id修改），
        equipMapper.updateEA(equipUpdateVo);
    }

    @Override
    public Equip scanInfo(UploadVo uploadVo) { ;
        //获取用户token和设备eid
        Integer uid = JwtHelper.getUserId(uploadVo.getToken());
        Integer eid = uploadVo.getEid();
        //根据eid判断设备状态是否是未启用
        Integer state = equipMapper.getStateByEid(eid);
        if(state == 0){
            throw new EquipException(ResultCodeEnum.EQUIP_STATE_ZERO);
        }else if(state == 3){ //表示设备维修中，需要去维修表中查询信息
            Integer isExist = repairMapper.isExistByUidAndEid(uid,eid);
            if(isExist == 0) {
                throw new EquipException(ResultCodeEnum.EQUIP_NOT_MATE);
            }
        } else {
            //根据用户id和eid去判断是否存在这条巡检计划安排 先去apartment_equip表中找到eid对应对的aid
            //然后去schedule表中判断是否存在uid和aid对应的数据，如果存在则获取数据，如果不存在则提示给前端信息代码
            Integer isExist = scheduleMapper.isExistByUidAndEid(uid,eid);
            if(isExist == 0){
                throw new EquipException(ResultCodeEnum.EQUIP_NOT_MATE);
            }
        }
        return equipMapper.findById(eid.toString());
    }

}
