package com.lyj.equipment.mapper;

import com.lyj.equipment.entity.Result;
import com.lyj.equipment.vo.result.ResultExportVo;
import com.lyj.equipment.vo.result.ResultListVo;
import com.lyj.equipment.vo.result.ResultPageVo;
import com.lyj.equipment.vo.result.ResultWebListVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ResultMapper {
    void insert(Result result);

    int getEquipConut(Integer eid);

    List<ResultWebListVo> getPageList(ResultPageVo resultPageVo);

    List<ResultWebListVo> getMyHistory(Integer uid);

    List<ResultExportVo> export();
}
