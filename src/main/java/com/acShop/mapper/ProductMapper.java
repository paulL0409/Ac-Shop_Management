package com.acShop.mapper;

import com.acShop.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from product ")
    List<Product> list();

    void delete(List<Integer> ids);

    @Insert("insert into product(shop_id, name, description, price, image_url, create_time)"+
            " values(#{shopId}, #{name}, #{description}, #{price}, #{imageUrl}, #{createTime})")
    void add(Product product);

    void update(Product product);
}
