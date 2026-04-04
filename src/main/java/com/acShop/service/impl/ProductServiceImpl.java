package com.acShop.service.impl;

import com.acShop.mapper.ProductMapper;
import com.acShop.pojo.PageBean;
import com.acShop.pojo.Product;
import com.acShop.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Cacheable(
            value = "productPage",
            key = "'page=' + #page + ':pageSize=' + #pageSize + ':shopId=' + #shopId + ':name=' + #name + ':begin=' + #begin + ':end=' + #end"
    )
    @Override
    public PageBean page(Integer page, Integer pageSize, Integer shopId, String name, BigDecimal begin, BigDecimal end) {
        PageHelper.startPage(page,pageSize);
        List<Product> productList = productMapper.list(shopId, name, begin, end);
        Page<Product> p = (Page<Product>)productList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @CacheEvict(value = "productPage", allEntries = true)
    @Override
    public void delete(List<Integer> ids) {
        productMapper.delete(ids);
    }

    @CacheEvict(value = "productPage", allEntries = true)
    @Override
    public void add(Product product) {
        product.setCreateTime(LocalDateTime.now());
        productMapper.add(product);
    }

    @CacheEvict(value = "productPage", allEntries = true)
    @Override
    public void update(Product product) {
        productMapper.update(product);
    }
}
