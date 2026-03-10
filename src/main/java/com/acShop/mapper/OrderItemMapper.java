package com.acShop.mapper;

import com.acShop.pojo.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
    @Insert("insert into orderItem(order_id, product_id, quantity, price) values(#{orderId}, #{productId}, #{quantity}, #{price})")
    public void add(OrderItem orderItem);
}
