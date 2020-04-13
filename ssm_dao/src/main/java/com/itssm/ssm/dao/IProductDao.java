package com.itssm.ssm.dao;

import com.itssm.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {

    /**
     * 查询所有的产品信息
     * @return
     */
    @Select("select * from product")
    List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product findById(String id);
}
