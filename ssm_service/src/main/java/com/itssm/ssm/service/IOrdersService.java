package com.itssm.ssm.service;

import com.itssm.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> findAll(int pages, int size) throws Exception;

    Orders findById(String ordersId);
}
