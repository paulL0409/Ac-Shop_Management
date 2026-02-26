package com.acShop.service.impl;

import com.acShop.mapper.ProductMapper;
import com.acShop.pojo.PageBean;
import com.acShop.pojo.Product;
import com.acShop.pojo.User;
import com.acShop.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.aop.framework.ProxyProcessorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Product> productList = productMapper.list();
        Page<Product> p = (Page<Product>)productList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        productMapper.delete(ids);
    }

    @Override
    public void add(Product product) {
        product.setCreateTime(LocalDateTime.now());
        productMapper.add(product);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }
}
