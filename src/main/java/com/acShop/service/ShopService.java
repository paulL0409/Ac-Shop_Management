package com.acShop.service;

import com.acShop.pojo.PageBean;
import com.acShop.pojo.Result;
import com.acShop.pojo.Shop;

import java.util.List;

public interface ShopService {
    PageBean page(Integer page, Integer pageSize, String name);

    void delete(List<Integer> ids);

    void add(Shop shop);

    void update(Shop shop);

    Shop getByOwnerId(Long userId);
}
