package com.lyj.equipment.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Repair {
    private Integer eid;
    private Integer uid;
    private Integer state;
    private LocalDateTime createTime;
    private LocalDateTime uploadTime;
    private String commit;
}
