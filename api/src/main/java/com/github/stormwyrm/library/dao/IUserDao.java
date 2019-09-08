package com.github.stormwyrm.library.dao;

import com.github.stormwyrm.library.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("userDao")
public interface IUserDao extends Mapper <User>{

    @Select("SELECT * FROM user WHERE username = #{usrname}")
    User getByName(String username);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User getByUsernamePassword(String username, String password);
}
