package com.acShop.service;

import com.acShop.pojo.PageBean;
import com.acShop.pojo.Product;

import java.util.List;

public interface ProductService {
    PageBean page(Integer page, Integer pageSize);

    void delete(List<Integer> ids);

    void add(Product product);

    void update(Product product);
}
