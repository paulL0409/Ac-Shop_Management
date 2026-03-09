package com.acShop.controller;

import com.acShop.pojo.Cart;
import com.acShop.pojo.CartVO;
import com.acShop.pojo.Result;
import com.acShop.service.CartService;
import com.acShop.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping
    public Result add(HttpServletRequest request,
                      @RequestBody Cart cart){
        String token = request.getHeader("token");
        Map<String, Object> claims = JwtUtils.parseJwt(token);
        Long userId = ((Number) claims.get("id")).longValue();
        cart.setUserId(userId);
        cartService.add(cart);
        return Result.success();
    }

    @GetMapping
    public Result get(HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String, Object> claims = JwtUtils.parseJwt(token);
        Long userId = ((Number) claims.get("id")).longValue();
        List<CartVO> list = cartService.get(userId);
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        cartService.delete(id);
        return Result.success();
    }

    @PutMapping("/{id}/increase")
    public Result increase(@PathVariable Long id){
        cartService.increase(id);
        return Result.success();
    }

    @PutMapping("/{id}/decrease")
    public Result decrease(@PathVariable Long id){
        cartService.decrease(id);
        return Result.success();
    }

}
