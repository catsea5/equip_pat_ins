package com.lyj.equipment.vo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="巡检结果上传模块")
public class UploadVo {
    @ApiModelProperty(value = "设备id")
    private Integer eid;
    @ApiModelProperty(value = "计划id")
    private Integer pid;
    @ApiModelProperty(value = "用户token")
    private String token;
    @ApiModelProperty(value = "设备状态")
    private Integer state;
    @ApiModelProperty(value = "注释")
    private String commit;
}
