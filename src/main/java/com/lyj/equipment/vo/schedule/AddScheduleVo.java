package com.lyj.equipment.vo.schedule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="安排表添加字段")
public class AddScheduleVo {
    @ApiModelProperty(value = "计划id")
    private Integer pid;
    @ApiModelProperty(value = "人员id")
    private Integer uid;
    @ApiModelProperty(value = "房间id")
    private Integer aid;
    @ApiModelProperty(value = "备注")
    private String commit;
}
