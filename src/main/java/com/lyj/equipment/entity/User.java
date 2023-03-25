package com.lyj.equipment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    @ApiModelProperty(value = "用户id")
    private int uid;

    @ApiModelProperty(value = "用户email")
    private String uemail;

    @ApiModelProperty(value = "用户密码")
    private String upassword;

    @ApiModelProperty(value = "用户姓名")
    private String uname;

    @ApiModelProperty(value = "用户权限")
    private int permission;

    @ApiModelProperty(value = "账号状态")
    private int ustate;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
