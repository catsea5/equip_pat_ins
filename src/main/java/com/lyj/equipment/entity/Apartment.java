package com.lyj.equipment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class Apartment {
    @ApiModelProperty(value = "房间id")
    private Integer aid;
    @ApiModelProperty(value = "房间号")
    private String anumber;
}
