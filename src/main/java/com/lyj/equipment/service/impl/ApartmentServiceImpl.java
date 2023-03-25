package com.lyj.equipment.service.impl;

import com.lyj.equipment.entity.Apartment;
import com.lyj.equipment.mapper.ApartmentMapper;
import com.lyj.equipment.service.ApartmentService;
import com.lyj.equipment.vo.apartment.ApartmentUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentMapper apartmentMapper;

    @Override
    public List<Apartment> findAnumber() {
        return apartmentMapper.findAnumber();
    }

    @Override
    public void addApartment(String anumber) {
        apartmentMapper.addApartment(anumber);
    }

    @Override
    public void updateAprtment(ApartmentUpdateVo apartmentUpdateVo) {
        apartmentMapper.updateApartment(apartmentUpdateVo);
    }

    @Override
    public List<Apartment> findByPid(Integer pid) {
        return apartmentMapper.findByPid(pid);
    }
}
