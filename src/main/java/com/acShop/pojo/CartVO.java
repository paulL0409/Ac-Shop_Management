package com.acShop.pojo;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {
    private Long id;
    private Integer quantity;

    private String name;
    private BigDecimal price;
    private String imageUrl;
    private BigDecimal totalPrice;
}
