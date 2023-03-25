package com.lyj.equipment.vo.result;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ResultExportVo {
    @ExcelProperty(value = "巡检计划")
    private String pname;
    @ExcelProperty(value = "巡检人员")
    private String uname;
    @ExcelProperty(value = "设备")
    private String ename;
    @ExcelProperty(value = "类型")
    private String type;
    @ExcelProperty(value = "所在位置")
    private String anumber;
    @ExcelProperty(value = "状态(1为正常,2为异常)")
    private String state;
    @ExcelProperty(value = "上传时间")
    private Date uploadTime;
    @ExcelProperty(value = "备注")
    private String commit;
}
