package com.itssm.ssm.dao;

import com.itssm.ssm.domain.Member;
import com.itssm.ssm.domain.Orders;
import com.itssm.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results(id = "resultMap",value = {
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "productId",column = "productId"),
            @Result(property = "memberId",column = "memberId"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one=@One(select = "IMemberDao.findById")),
            //多对多，根据中间表查询。提供orders的id去中间表中查询到traveller的id，再查询到traveller的表的信息。
            @Result(property = "travellers",column = "id",many = @Many(select = "ITravellerDao.findById"))

    })
    public List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{id}")
    @ResultMap(value = {"resultMap"})
    Orders findById(String ordersId);
}
