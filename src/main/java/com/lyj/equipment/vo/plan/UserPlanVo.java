package com.lyj.equipment.vo.plan;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="用户计划对象")
public class UserPlanVo {
    @ApiModelProperty(value = "用户token")
    private String token;
}
