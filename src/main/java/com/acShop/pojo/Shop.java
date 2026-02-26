package com.acShop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    private Long id;
    private Long ownerId;            // 對應 owner_id
    private String name;
    private LocalDateTime createTime;
}
