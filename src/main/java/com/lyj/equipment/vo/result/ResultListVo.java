package com.lyj.equipment.vo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description="巡检结果模板")
public class ResultListVo {
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
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;
    @ApiModelProperty(value = "备注")
    private String commit;
}
