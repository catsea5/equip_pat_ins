package com.lyj.equipment.vo.repair;

import lombok.Data;

import java.util.Date;

@Data
public class myRepairVo {
    private Integer eid;
    private String ename;
    private String type;
    private Integer aid;
    private String anumber;
    private Date createTime;
    private String commit;
}
