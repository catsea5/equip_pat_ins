package com.lyj.equipment.mapper;

import com.lyj.equipment.entity.Apartment;
import com.lyj.equipment.vo.apartment.ApartmentUpdateVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ApartmentMapper {
    List<Apartment> findAnumber();

    void addApartment(String anumber);

    void updateApartment(ApartmentUpdateVo apartmentUpdateVo);

    List<Apartment> findByPid(Integer pid);

}
