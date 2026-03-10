package com.acShop.service;

import com.acShop.pojo.Order;

public interface OrderService {
    void checkout(Long userId);

    void add(Order order);
}
