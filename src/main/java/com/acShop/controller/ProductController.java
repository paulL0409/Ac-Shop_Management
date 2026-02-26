package com.acShop.controller;

import com.acShop.pojo.PageBean;
import com.acShop.pojo.Product;
import com.acShop.pojo.Result;
import com.acShop.pojo.User;
import com.acShop.service.ProductService;
import com.acShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = productService.page(page, pageSize);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        productService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Product product){
        productService.add(product);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Product product){
        productService.update(product);
        return Result.success();
    }
}
