package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByPhone(Long phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPhoneSelective(User record);

    int updateByPrimaryKey(User record);
}