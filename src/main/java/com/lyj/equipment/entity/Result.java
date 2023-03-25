package com.lyj.equipment.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Result {
    private Integer pid;
    private Integer uid;
    private Integer eid;
    private Integer state;
    private LocalDateTime uploadTime;
    private String commit;

}
