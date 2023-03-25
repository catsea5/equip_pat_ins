package com.lyj.equipment.vo.schedule;

import com.lyj.equipment.entity.Equip;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApartmentEquipListVo {
    @ApiModelProperty(value = "房间id")
    private Integer aid;
    @ApiModelProperty(value = "房间号")
    private String anumber;
    @ApiModelProperty(value = "备注")
    private String commit;
    @ApiModelProperty(value = "设备列表")
    private List<Equip> equipList;
}
