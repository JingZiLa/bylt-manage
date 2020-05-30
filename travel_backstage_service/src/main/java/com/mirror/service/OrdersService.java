package com.mirror.service;

import com.mirror.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/2.
 * 订单信息业务层接口
 */

public interface OrdersService {

    List<Orders> findAll(int page,int pagesize) throws Exception;

    Orders fingById(String id)throws Exception;
}
