package com.lyj.equipment.vo.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ResultChildVo {
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;
    @ApiModelProperty(value = "备注")
    private String commit;
}
