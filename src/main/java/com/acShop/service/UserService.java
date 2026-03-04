package com.acShop.service;

import com.acShop.pojo.PageBean;
import com.acShop.pojo.User;

import java.util.List;

public interface UserService {

    PageBean page(Integer page, Integer pageSize, String username,  String role);

    void delete(List<Integer> ids);

    void add(User user);

    void update(User user);

    User login(User user);
}
