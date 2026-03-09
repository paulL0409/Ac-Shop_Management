package com.acShop.service;

import com.acShop.pojo.Cart;
import com.acShop.pojo.CartVO;

import java.util.List;

public interface CartService {
    void add(Cart cart);

    List<CartVO> get(Long userId);

    void delete(Long id);

    void increase(Long id);

    void decrease(Long id);
}
