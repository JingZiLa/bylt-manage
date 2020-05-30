package com.mirror.service.impl;

import com.github.pagehelper.PageHelper;
import com.mirror.dao.OrdersDao;
import com.mirror.domain.Orders;
import com.mirror.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/2.
 * 订单信息业务层
 */
@Service("ordersService")
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int pagesize) throws Exception {
        PageHelper.startPage(page,pagesize);
        return ordersDao.findAll();
    }

    @Override
    public Orders fingById(String id) throws Exception {
        return ordersDao.findById(id);
    }

}
