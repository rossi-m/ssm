package com.itssm.ssm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.itssm.ssm.dao.IOrdersDao;
import com.itssm.ssm.domain.Orders;
import com.itssm.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int pages, int size) throws Exception {
        //在真正执行sql前，使用PageHelper前来完成分页。
        //前值是页码值，后置每页显示条数
        PageHelper.startPage(pages,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
