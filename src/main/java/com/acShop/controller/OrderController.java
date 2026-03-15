package com.acShop.controller;

import com.acShop.pojo.Order;
import com.acShop.pojo.Result;
import com.acShop.service.OrderService;
import com.acShop.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public Result checkout(HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String, Object> claims = JwtUtils.parseJwt(token);
        Long userId = ((Number) claims.get("id")).longValue();
        return Result.success(orderService.checkout(userId));
    }

    @GetMapping
    public Result list(HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String, Object> claims = JwtUtils.parseJwt(token);
        Long userId = ((Number) claims.get("id")).longValue();
        List<Order> list = orderService.listByUserId(userId);
        return Result.success(list);
    }
}
