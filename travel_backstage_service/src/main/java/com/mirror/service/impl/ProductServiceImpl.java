package com.mirror.service.impl;

import com.github.pagehelper.PageHelper;
import com.mirror.dao.ProductDao;
import com.mirror.domain.Product;
import com.mirror.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/2/29.
 * 产品业务层接口实现
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public List<Product> findAll(int page,int pagesize) throws Exception{
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page,pagesize);
        return productDao.findAll();
    }

    @Override
    public void saveProduct(Product product) throws Exception{
        productDao.saveProduct(product);
    }
}
