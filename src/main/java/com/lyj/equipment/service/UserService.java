package com.lyj.equipment.service;

import com.github.pagehelper.PageInfo;
import com.lyj.equipment.entity.User;
import com.lyj.equipment.vo.user.PageVo;
import com.lyj.equipment.vo.user.UserListVo;
import com.lyj.equipment.vo.user.UserUpdateVo;

import java.util.List;

public interface UserService {
    PageInfo<User> findByPage(PageVo pageVo);


//    void delete(Integer id);

    void update(UserUpdateVo userUpdateVo);

    User queryById(Integer id);

//    void confirmOrDeclineById(Integer id,Integer state);

    void change(Integer id, Integer state);

    PageInfo<User> findApplyByPage(PageVo pageVo);

    List<UserListVo> findIdAndName();
}
