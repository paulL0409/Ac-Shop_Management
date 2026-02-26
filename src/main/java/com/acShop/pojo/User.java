package com.acShop.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    @JsonProperty("password")  // 接受前端傳 "password"，對應到 userPassword
    private String userPassword;
    private String role;
    private LocalDateTime createTime;
}
