package com.acShop.mapper;

import com.acShop.pojo.Cart;
import com.acShop.pojo.CartVO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface CartMapper {
    @Select("Select * from cart where user_id = #{userId} and product_id = #{productId}")
    Cart getCartByUserAndProduct(Cart cart);

    @Insert("Insert into cart(user_id, product_id, quantity, create_time)" +
            " values(#{userId}, #{productId}, #{quantity}, #{createTime})")
    void insert(Cart cart);

    @Update("update cart set quantity = quantity + 1 where id = #{id}")
    void update(Cart cart);

    @Select("select c.id, c.quantity, p.name, p.price, p.image_url, (c.quantity * p.price) as total_price from cart c join product p on c.product_id = p.id where c.user_id = #{userId}")
    List<CartVO> get(Long userId);

    @Delete("delete from cart where id = #{id}")
    void delete(Long id);

    @Update("update cart set quantity = quantity + 1 where id = #{id}")
    void increse(Long id);

    @Update("update cart set quantity = quantity - 1 where id = #{id}")
    void decrease(Long id);

    @Select("select * from cart where user_id = #{userId}")
    List<Cart> getCartByUserId(Long userId);

    @Delete("delete from cart where user_id = #{userId}")
    void deleteByUserId(Long userId);
}
