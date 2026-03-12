package com.acShop.controller;

import com.acShop.pojo.Result;
import com.acShop.pojo.User;
import com.acShop.service.UserService;
import com.acShop.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Result login(@RequestBody User user){
        try {
            User u = userService.login(user);
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            claims.put("role", u.getRole());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        catch (RuntimeException e){
            Result r = Result.error(e.getMessage());
            System.out.println("RETURN RESULT = " + r);
            return r;
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }
}
