package com.mirror.dao;

import com.mirror.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/2.
 *
 */
@Repository
public interface TravellersDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId)throws Exception;
}
