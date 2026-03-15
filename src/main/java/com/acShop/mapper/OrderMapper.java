package com.acShop.mapper;

import com.acShop.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into orders(user_id, total_amount, status, create_time) values(#{userId}, #{totalAmount}, #{status}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Order order);

    @Select("select * from orders where user_id = #{userId}")
    List<Order> listByUserId(Long userId);

    @Select("select * from orders where id = #{orderId}")
    Order findById(Long orderId);

    @Update("update orders set stripe_payment_intent_id = #{paymentIntentId} where id = #{orderId}")
    void updateStripePaymentIntentId(Long orderId, String paymentIntentId);

    @Select("select * from orders where stripe_payment_intent_id = #{paymentIntentId}")
    Order findByPaymentIntentId(@Param("paymentIntentId") String paymentIntentId);

    @Update("update orders set status = 'PAID', paid_time = now() where id = #{id}")
    void markAsPaid(@Param("id") Long id);

    @Update("update orders set status = 'FAILED' where id = #{id}")
    void markAsFailed(@Param("id") Long id);

}
