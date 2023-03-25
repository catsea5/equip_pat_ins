package com.lyj.equipment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Plan {
    @ApiModelProperty(value = "巡检计划id")
    private Integer pid;
    @ApiModelProperty(value = "巡检计划名称")
    private String pname;
    @ApiModelProperty(value = "巡检计划状态")
    private Integer state;
    @ApiModelProperty(value = "是否删除")
    private Integer isdelete;
    @ApiModelProperty(value = "巡检计划开始时间")
    private Date startTime;
    @ApiModelProperty(value = "巡检计划截止时间")
    private Date endTime;
}
