package com.itssm.ssm.dao;

import com.itssm.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 多对多
 */
@Repository
public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{id})")
    public List<Traveller> findById(String ordersId);
}
