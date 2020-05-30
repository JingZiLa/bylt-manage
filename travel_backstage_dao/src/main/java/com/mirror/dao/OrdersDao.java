package com.mirror.dao;

import com.mirror.domain.Member;
import com.mirror.domain.Orders;
import com.mirror.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/2.
 * 订单信息Dao
 */
@Repository
public interface OrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.mirror.dao.ProductDao.findByProductId")),
    })
     List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.mirror.dao.ProductDao.findByProductId")),
            @Result(property = "member", column = "memberId",javaType = Member.class,one = @One(select = "com.mirror.dao.MemberDao.findByMemberId")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class , many = @Many(select = "com.mirror.dao.TravellersDao.findByOrdersId"))
    })
    Orders findById(String id)throws Exception;
}
