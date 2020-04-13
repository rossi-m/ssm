package com.itssm.ssm.service.Impl;

import com.itssm.ssm.service.IProductService;
import com.itssm.ssm.dao.IProductDao;
import com.itssm.ssm.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private IProductDao dao;

    @Override
    public List<Product> findAll() throws Exception {
        return dao.findAll();
    }

    @Override
    public void save(Product product)throws Exception {
        dao.save(product);
    }
}
