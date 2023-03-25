package com.lyj.equipment.vo.email;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RepairEmailInfo {
    @ApiModelProperty(value = "巡检人员名")
    private String uname;
    @ApiModelProperty(value = "巡检人员email")
    private String uemail;
    @ApiModelProperty(value = "设备名")
    private String ename;
    @ApiModelProperty(value = "设备类型")
    private String type;
    @ApiModelProperty(value = "房间号")
    private String anumber;
}
