package com.lyj.equipment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Schedule {
    @ApiModelProperty(value = "巡检计划id")
    private Integer pid;
    @ApiModelProperty(value = "巡检人员id")
    private Integer uid;
    @ApiModelProperty(value = "房间id")
    private Integer aid;
    @ApiModelProperty(value = "完成状态")
    private Integer state;
    @ApiModelProperty(value = "备注")
    private String commit;
}
