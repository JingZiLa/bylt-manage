package com.mirror.service;

import com.mirror.domain.Product;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/2/29.
 * 产品业务层接口
 */
public interface ProductService {

    public List<Product> findAll(int page,int pagesize)throws Exception;

    void saveProduct(Product product)throws Exception;
}
