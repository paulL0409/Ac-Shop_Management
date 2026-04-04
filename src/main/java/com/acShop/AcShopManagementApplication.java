package com.acShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AcShopManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcShopManagementApplication.class, args);
    }

}
