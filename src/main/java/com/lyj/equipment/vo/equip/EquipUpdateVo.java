package com.lyj.equipment.vo.equip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="设备修改对象")
public class EquipUpdateVo {
    @ApiModelProperty(value = "设备id")
    private Integer id;
    @ApiModelProperty(value = "设备名")
    private String name;
    @ApiModelProperty(value = "设备类型")
    private String type;
    @ApiModelProperty(value = "房间id")
    private Integer aid;
    @ApiModelProperty(value = "设备状态")
    private Integer state;
}
