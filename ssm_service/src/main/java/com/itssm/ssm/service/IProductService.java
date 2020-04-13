package com.itssm.ssm.service;

import com.itssm.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll() throws  Exception;

    void save(Product product) throws Exception;
}
