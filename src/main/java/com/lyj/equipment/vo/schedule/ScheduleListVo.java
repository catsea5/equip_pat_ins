package com.lyj.equipment.vo.schedule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="安排表前端展示模块")
public class ScheduleListVo {
    @ApiModelProperty(value = "人员id")
    private Integer uid;
    @ApiModelProperty(value = "人员姓名")
    private String uname;
    @ApiModelProperty(value = "房间id")
    private Integer aid;
    @ApiModelProperty(value = "房间号")
    private String anumber;
    @ApiModelProperty(value = "巡检状态")
    private Integer state;
    @ApiModelProperty(value = "备注")
    private String commit;
}
