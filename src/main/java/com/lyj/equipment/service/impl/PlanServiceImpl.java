package com.lyj.equipment.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.Plan;
import com.lyj.equipment.mapper.PlanMapper;
import com.lyj.equipment.service.PlanService;
import com.lyj.equipment.vo.plan.PlanPageVo;
import com.lyj.equipment.vo.plan.PlanUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanMapper planMapper;

    @Override
    public PageInfo<Plan> findByPage(PlanPageVo planPageVo) {
        //创建分页
        Page<Object> objects = PageHelper.startPage(planPageVo.getPage(), planPageVo.getLimit());
        List<Plan> list;
        list = planMapper.findByPage(planPageVo);
        PageInfo<Plan> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    // 检查巡检计划是否过期
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void isPastDue(){
        //修正时间
        TimeZone time = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        planMapper.changeStateByTime(simpleDateFormat.format(new Date()));
    }
    @Override
    public void addPlan(PlanUpdateVo planAddVo) {
        Plan plan = new Plan();
        plan.setPname(planAddVo.getName());
        plan.setState(1);
        plan.setIsdelete(0);
        plan.setStartTime(planAddVo.getStartTime());
        plan.setEndTime(planAddVo.getEndTime());
        planMapper.insert(plan);
    }

    @Override
    public void updatePlay(PlanUpdateVo planUpdateVo) {
        planMapper.update(planUpdateVo);
    }

    @Override
    public void deletePlan(Integer id) {
        planMapper.delete(id);
    }

    @Override
    public Plan queryById(Integer id) {
        return planMapper.queryById(id);
    }

    @Override
    public List<Plan> listByState(Integer state) {
        return planMapper.findByState(state);
    }

    @Override
    public List<Plan> getPlanByUId(Integer userId) {
        return planMapper.getPlanByUid(userId);
    }
}
