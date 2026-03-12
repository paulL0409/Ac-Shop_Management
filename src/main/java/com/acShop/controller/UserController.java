package com.acShop.controller;

import com.acShop.pojo.PageBean;
import com.acShop.pojo.Result;
import com.acShop.pojo.User;
import com.acShop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users")
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String username,  String role){
        PageBean pageBean = userService.page(page, pageSize, username, role);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        userService.delete(ids);
        return Result.success();
    }


    @PutMapping
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }
}
