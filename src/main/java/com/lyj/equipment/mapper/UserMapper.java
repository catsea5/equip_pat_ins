package com.lyj.equipment.mapper;

import com.lyj.equipment.entity.User;
import com.lyj.equipment.vo.user.PageVo;
import com.lyj.equipment.vo.user.UserListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {


//    void deleteById(Integer id);

    void updateById(User user);

    User queryById(Integer id);

    List<User> findByKeyword(PageVo pageVo);

//    void confirmOrDeclineById(@Param("id") Integer id,@Param("state") Integer state);

    void change(@Param("id") Integer id, @Param("state") Integer state);

    List<User> findApplyByKeyword(PageVo pageVo);

    List<UserListVo> findIdAndName();
}
