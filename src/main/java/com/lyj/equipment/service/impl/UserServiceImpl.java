package com.lyj.equipment.service.impl;

import com.lyj.equipment.entity.User;
import com.lyj.equipment.mapper.UserMapper;
import com.lyj.equipment.service.UserService;
import com.lyj.equipment.vo.user.PageVo;
import com.lyj.equipment.vo.user.UserListVo;
import com.lyj.equipment.vo.user.UserUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo<User> findByPage(PageVo pageVo) {
        //创建分页
        Page<Object> objects = PageHelper.startPage(pageVo.getPage(), pageVo.getLimit());
        List<User> list;
        list = userMapper.findByKeyword(pageVo);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
//    @Override
//    public void delete(Integer id) {
//        userMapper.deleteById(id);
//    }

    @Override
    public void update(UserUpdateVo userUpdateVo) {
        User user = new User();
        user.setUid(userUpdateVo.getId());
        user.setUemail(userUpdateVo.getEmail());
        user.setUname(userUpdateVo.getName());
        user.setUpassword(userUpdateVo.getPassword());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    @Override
    public User queryById(Integer id) {
        User user = userMapper.queryById(id);
        return user;
    }

//    @Override
//    public void confirmOrDeclineById(Integer id,Integer state) {
//        userMapper.confirmOrDeclineById(id,state);
//    }

    @Override
    public void change(Integer id, Integer state) {
        userMapper.change(id,state);
    }

    @Override
    public PageInfo<User> findApplyByPage(PageVo pageVo) {
        //创建分页
        Page<Object> objects = PageHelper.startPage(pageVo.getPage(), pageVo.getLimit());
        List<User> list;
        list = userMapper.findApplyByKeyword(pageVo);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<UserListVo> findIdAndName() {
        return userMapper.findIdAndName();
    }
}
