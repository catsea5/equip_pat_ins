package com.lyj.equipment.vo.schedule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="微信前端传入的查询信息，查询房间号和设备信息")
public class WxInfoVo {
    @ApiModelProperty(value = "巡检计划id")
    private Integer pid;
    @ApiModelProperty(value = "用户token")
    private String token;
}
