package com.acShop.service;

import com.acShop.pojo.PageBean;
import com.acShop.pojo.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    PageBean page(Integer page, Integer pageSize, Integer shopId, String name, BigDecimal begin, BigDecimal end);

    void delete(List<Integer> ids);

    void add(Product product);

    void update(Product product);
}
