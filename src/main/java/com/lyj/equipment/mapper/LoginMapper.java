package com.lyj.equipment.mapper;

import com.lyj.equipment.entity.User;
import com.lyj.equipment.vo.login.RePasswordVo;
import com.lyj.equipment.vo.user.UserVo;
import org.springframework.stereotype.Component;
@Component
public interface LoginMapper {

    User selectByEmail(String email);

    void insert(User user);
    Integer isExist(String email);

    void update(RePasswordVo rePasswordVo);
}
