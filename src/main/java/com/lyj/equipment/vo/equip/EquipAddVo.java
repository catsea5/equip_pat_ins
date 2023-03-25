package com.lyj.equipment.vo.equip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="设备添加对象")
public class EquipAddVo {
    @ApiModelProperty(value = "设备名")
    private String name;
    @ApiModelProperty(value = "设备类型")
    private String type;
    @ApiModelProperty(value = "房间id")
    private Integer aid;
    @ApiModelProperty(value = "资产编号")
    private String assetCode;
    @ApiModelProperty(value = "备注")
    private String commit;
}
