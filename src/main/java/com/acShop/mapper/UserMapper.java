package com.acShop.mapper;

import com.acShop.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user ")
    List<User> list();

    void delete(List<Integer> ids);

    @Insert("insert into user(username, user_password, role, create_time)"+
            " values(#{username}, #{userPassword}, #{role}, #{createTime})")
    void add(User user);

    void update(User user);

    @Select("select * from user where username = #{username} and user_password = #{userPassword}")
    User findUserByNameAndPassword(User user);
}
