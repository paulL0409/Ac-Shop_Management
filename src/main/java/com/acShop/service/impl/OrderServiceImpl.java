package com.acShop.service.impl;

import com.acShop.mapper.CartMapper;
import com.acShop.mapper.OrderItemMapper;
import com.acShop.mapper.OrderMapper;
import com.acShop.mapper.ProductMapper;
import com.acShop.pojo.Cart;
import com.acShop.pojo.Order;
import com.acShop.pojo.OrderItem;
import com.acShop.pojo.Product;
import com.acShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Long checkout(Long userId) {
        List<Cart> cartList = cartMapper.getCartByUserId(userId);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Cart cart : cartList) {
            Product product = productMapper.getById(cart.getProductId());
            int quantity = cart.getQuantity();
            BigDecimal price = product.getPrice();
            totalAmount = totalAmount.add(price.multiply(BigDecimal.valueOf(quantity)));
        }
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setStatus("UNPAID");
        order.setCreateTime(LocalDateTime.now());
        orderMapper.add(order);

        for(Cart cart : cartList){
            Product product = productMapper.getById(cart.getProductId());
            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setProductId(cart.getProductId());
            item.setQuantity(cart.getQuantity());
            item.setPrice(product.getPrice());

            orderItemMapper.add(item);

        }
        cartMapper.deleteByUserId(userId);
        return order.getId();

    }

    @Override
    public List<Order> listByUserId(Long userId) {
        return orderMapper.listByUserId(userId);
    }

}
