package com.lyj.equipment.service;

import com.github.pagehelper.PageInfo;
import com.lyj.equipment.vo.result.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ResultService {
    void upload(UploadVo uploadVo);

    PageInfo<ResultWebListVo> getResultInfoList(ResultPageVo resultPageVo);

    void exportData(HttpServletResponse response);

    List<ResultWebListVo> getMyHistory(UserHistoryVo userHistoryVo);
}
