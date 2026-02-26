package com.acShop.service;

import com.acShop.pojo.PageBean;
import com.acShop.pojo.Shop;

import java.util.List;

public interface ShopService {
    PageBean page(Integer page, Integer pageSize);

    void delete(List<Integer> ids);

    void add(Shop shop);

    void update(Shop shop);
}
