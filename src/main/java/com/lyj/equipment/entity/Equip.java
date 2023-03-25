package com.lyj.equipment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Equip {
    @ApiModelProperty(value = "设备id")
    private Integer eid;
    @ApiModelProperty(value = "设备名称")
    private String ename;
    @ApiModelProperty(value = "设备类型")
    private String type;
    @ApiModelProperty(value = "资产编号")
    private String assetCode;
    @ApiModelProperty(value = "设备状态")
    private Integer state;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "备注")
    private String commit;

    private Apartment apartment;
}
