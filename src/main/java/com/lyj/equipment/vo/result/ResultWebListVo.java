package com.lyj.equipment.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultWebListVo {
    @ApiModelProperty(value = "巡检计划id")
    private Integer pid;
    @ApiModelProperty(value = "巡检计划名")
    private String pname;
    @ApiModelProperty(value = "巡检人员id")
    private Integer uid;
    @ApiModelProperty(value = "巡检人员名")
    private String uname;
    @ApiModelProperty(value = "设备id")
    private Integer eid;
    @ApiModelProperty(value = "设备名")
    private String ename;
    @ApiModelProperty(value = "设备类型")
    private String type;
    @ApiModelProperty(value = "房间id")
    private Integer aid;
    @ApiModelProperty(value = "房间号")
    private String anumber;
    @ApiModelProperty(value = "携带数据")
    private List<ResultChildVo> childList;
}
