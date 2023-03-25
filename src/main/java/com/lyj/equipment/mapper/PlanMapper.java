package com.lyj.equipment.mapper;

import com.lyj.equipment.entity.Plan;
import com.lyj.equipment.vo.plan.PlanPageVo;
import com.lyj.equipment.vo.plan.PlanUpdateVo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface PlanMapper {
    List<Plan> findByPage(PlanPageVo planPageVo);

    void insert(Plan plan);

    void update(PlanUpdateVo planUpdateVo);

    void delete(Integer id);


    void changeStateByTime(String date);

    Plan queryById(Integer id);

    List<Plan> findByState(Integer state);

    List<Plan> getPlanByUid(Integer userId);
}
