package com.lyj.equipment.controller;

import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.Plan;
import com.lyj.equipment.service.PlanService;
import com.lyj.equipment.util.JwtHelper;
import com.lyj.equipment.util.Result;
import com.lyj.equipment.vo.plan.PlanPageVo;
import com.lyj.equipment.vo.plan.PlanUpdateVo;
import com.lyj.equipment.vo.plan.UserPlanVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("plan")
@Api(tags={"巡检计划模块api"})
public class PlanController {
    @Autowired
    private PlanService planService;
    //分页展示巡检计划（条件查询包括巡检计划名、开始时间-截止时间）
    //每次获取的时候都要去进行一个时间检测，如果时间过了结束时间就要改变状态
    @ApiOperation(value="获取巡检计划列表")
    @PostMapping("list")
    public Result planList(@RequestBody PlanPageVo planPageVo){
        PageInfo<Plan> planPageInfo = planService.findByPage(planPageVo);
        return Result.ok(planPageInfo);
    }
    //添加巡检计划
    @ApiOperation(value="添加巡检计划")
    @PostMapping("add")
    public Result add(@RequestBody PlanUpdateVo PlanAddVo){
        planService.addPlan(PlanAddVo);
        return Result.ok();
    }
    //根据id查询计划
    @ApiOperation(value="根据id查询计划")
    @GetMapping("query/{id}")
    public Result queryById(@PathVariable Integer id){
       Plan plan = planService.queryById(id);
       return Result.ok(plan);
    }
    //修改巡检计划（只能修改还未进行的计划）
    @ApiOperation(value="修改巡检计划（只能修改还未进行的计划）")
    @PostMapping("update")
    public Result update(@RequestBody PlanUpdateVo planUpdateVo){
        planService.updatePlay(planUpdateVo);
        return Result.ok();
    }
    //删除巡检计划（只能删除还未进行的巡检计划，将isdelete改为0）
    @ApiOperation(value="删除巡检计划（只能删除还未进行的巡检计划，将isdelete改为0）")
    @GetMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        planService.deletePlan(id);
        return Result.ok();
    }
    //获取按照状态获取巡检计划列表
    @ApiOperation(value="获取按照状态获取巡检计划列表")
    @GetMapping("list/{state}")
    public Result listById(@PathVariable Integer state){
       List<Plan> list =  planService.listByState(state);
        return Result.ok(list);
    }
    //先根据aid获取到pid再通过pid获取到plan
    @ApiOperation(value="根据用户获得用户巡检计划")
    @PostMapping("userplan")
    public Result userPlan(@RequestBody UserPlanVo userPlanVo){
        List<Plan> list = planService.getPlanByUId(JwtHelper.getUserId(userPlanVo.getToken()));
        return Result.ok(list);
    }
}
