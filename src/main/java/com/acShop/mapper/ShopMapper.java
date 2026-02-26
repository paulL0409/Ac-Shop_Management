package com.acShop.mapper;

import com.acShop.pojo.Shop;
import com.acShop.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShopMapper {
    @Select("select * from shop ")
    List<Shop> list();

    void delete(List<Integer> ids);

    @Insert("insert into shop(owner_id, name, create_time)"+
            " values(#{ownerId}, #{name}, #{createTime})")
    void add(Shop shop);

    void update(Shop shop);
}
