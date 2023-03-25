package com.lyj.equipment.vo.equip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="设备分页对象")
public class EquipPageVo {
    @ApiModelProperty(value = "当前页")
    private Integer page;
    @ApiModelProperty(value = "每页数据个数")
    private Integer limit;
    @ApiModelProperty(value = "查询条件")
    private String keyword;
    @ApiModelProperty(value = "设备类型")
    private String type;
    @ApiModelProperty(value = "房间号")
    private String anumber;
}
