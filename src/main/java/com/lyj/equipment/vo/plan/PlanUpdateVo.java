package com.lyj.equipment.vo.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description="巡检计划修改对象")
public class PlanUpdateVo {
    @ApiModelProperty(value = "巡检计划id")
    private Integer pid;
    @ApiModelProperty(value = "巡检计划名")
    private String name;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
