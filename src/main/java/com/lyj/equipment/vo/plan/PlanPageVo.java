package com.lyj.equipment.vo.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@ApiModel(description="巡检计划分页对象")
public class PlanPageVo {
    @ApiModelProperty(value = "当前页")
    private Integer page;
    @ApiModelProperty(value = "每页数据个数")
    private Integer limit;
    @ApiModelProperty(value = "查询条件")
    private String keyword;
    @ApiModelProperty(value = "状态")
    private Integer state;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
