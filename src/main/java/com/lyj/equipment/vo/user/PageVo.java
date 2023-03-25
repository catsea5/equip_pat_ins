package com.lyj.equipment.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="分页对象")
public class PageVo {
    @ApiModelProperty(value = "当前页")
    private Integer page;
    @ApiModelProperty(value = "每页数据个数")
    private Integer limit;
    @ApiModelProperty(value = "查询条件")
    private String keyword;

}
