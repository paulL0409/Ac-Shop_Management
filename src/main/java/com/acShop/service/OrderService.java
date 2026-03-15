package com.acShop.service;

import com.acShop.pojo.Order;

import java.util.List;

public interface OrderService {
    Long checkout(Long userId);

    List<Order> listByUserId(Long userId);
}
