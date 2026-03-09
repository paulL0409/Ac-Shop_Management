package com.acShop.service.impl;

import com.acShop.mapper.CartMapper;
import com.acShop.pojo.Cart;
import com.acShop.pojo.CartVO;
import com.acShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public void add(Cart cart) {
        Cart cart1 = cartMapper.getCartByUserAndProduct(cart);
        if(cart1 == null){
            cart.setQuantity(1);
            cart.setCreateTime(LocalDateTime.now());
            cartMapper.insert(cart);
        }else{
            cartMapper.update(cart1);
        }
    }

    @Override
    public List<CartVO> get(Long userId) {
        return cartMapper.get(userId);
    }

    @Override
    public void delete(Long id) {
        cartMapper.delete(id);
    }

    @Override
    public void increase(Long id) {
        cartMapper.increse(id);
    }

    @Override
    public void decrease(Long id) {
        cartMapper.decrease(id);
    }
}
