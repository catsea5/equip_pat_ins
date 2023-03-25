package com.lyj.equipment.vo.email;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleEmailInfo {
    @ApiModelProperty(value = "巡检人员名")
    private String uname;
    @ApiModelProperty(value = "巡检人员email")
    private String uemail;
    @ApiModelProperty(value = "房间号")
    private String anumber;
    @ApiModelProperty(value = "计划名")
    private String pname;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "截止时间")
    private Date endTime;
}
