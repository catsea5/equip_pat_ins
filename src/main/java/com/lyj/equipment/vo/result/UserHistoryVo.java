package com.lyj.equipment.vo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="用户巡检历史查询对象")
public class UserHistoryVo {
    @ApiModelProperty(value = "用户token")
    private String token;
}
