package com.lyj.equipment.service;

import com.lyj.equipment.vo.login.LoginVo;
import com.lyj.equipment.vo.login.LogonVo;
import com.lyj.equipment.vo.login.RePasswordVo;
import com.lyj.equipment.vo.user.UserVo;

import java.util.Map;

public interface LoginService {
    //随机获得验证码
    String getCode();
    //发送邮件
    void sendEmail(String email, String code);

    void logon(LogonVo logonVo);

    Map<String, Object> login(LoginVo loginVo);

    void change(RePasswordVo rePasswordVo);

    String adminLogin(LoginVo loginVo);
}
