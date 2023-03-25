package com.lyj.equipment.vo.apartment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="房间号修改对角")
public class ApartmentUpdateVo {
    @ApiModelProperty(value = "房间id")
    private Integer aid;
    @ApiModelProperty(value = "房间号")
    private String anumber;

}
