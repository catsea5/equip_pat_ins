package com.lyj.equipment.service;

import com.lyj.equipment.entity.Apartment;
import com.lyj.equipment.vo.apartment.ApartmentUpdateVo;

import java.util.List;

public interface ApartmentService {
    List<Apartment> findAnumber();

    void addApartment(String anumber);

    void updateAprtment(ApartmentUpdateVo apartmentUpdateVo);

    List<Apartment> findByPid(Integer pid);
}
