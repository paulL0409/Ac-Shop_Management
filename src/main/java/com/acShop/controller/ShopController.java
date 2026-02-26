package com.acShop.controller;

import com.acShop.pojo.PageBean;
import com.acShop.pojo.Result;
import com.acShop.pojo.Shop;
import com.acShop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = shopService.page(page, pageSize);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        shopService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Shop shop){
        shopService.add(shop);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Shop shop){
        shopService.update(shop);
        return Result.success();
    }
}
