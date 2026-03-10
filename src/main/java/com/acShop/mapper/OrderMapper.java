package com.acShop.mapper;

import com.acShop.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into orders(user_id, total_amount, status, create_time) values(#{userId}, #{totalAmount}, #{status}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Order order);

    @Select("select * from orders where user_id = #{userId}")
    List<Order> listByUserId(Long userId);
}
