package com.acShop.controller;

import com.acShop.pojo.Order;
import com.acShop.pojo.Result;
import com.acShop.service.OrderService;
import com.acShop.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Result addOrder(@RequestBody Order order){
        orderService.add(order);
        return Result.success();
    }
    @PostMapping("/checkout")
    public Result checkout(HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String, Object> claims = JwtUtils.parseJwt(token);
        Long userId = ((Number) claims.get("id")).longValue();
        orderService.checkout(userId);
        return Result.success();
    }
}
