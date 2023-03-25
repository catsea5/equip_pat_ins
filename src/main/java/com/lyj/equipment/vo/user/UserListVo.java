package com.lyj.equipment.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserListVo {
    @ApiModelProperty(value = "uid")
    private Integer uid;
    @ApiModelProperty(value = "用户名")
    private String uname;
}
