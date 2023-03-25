package com.lyj.equipment.service;

import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.Plan;
import com.lyj.equipment.vo.plan.PlanPageVo;
import com.lyj.equipment.vo.plan.PlanUpdateVo;

import java.util.List;

public interface PlanService {
    PageInfo<Plan> findByPage(PlanPageVo planPageVo);

    void addPlan(PlanUpdateVo planAddVo);

    void updatePlay(PlanUpdateVo planUpdateVo);

    void deletePlan(Integer id);

    Plan queryById(Integer id);

    List<Plan> listByState(Integer state);

    List<Plan> getPlanByUId(Integer userId);
}
