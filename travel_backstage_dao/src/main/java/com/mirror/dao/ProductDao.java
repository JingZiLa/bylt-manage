package com.mirror.dao;

import com.mirror.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/2/29.
 * 产品Dao接口
 */
@Repository
public interface ProductDao {

    @Select("select * from product")
    public List<Product> findAll()throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product)throws Exception;

    //根据id查询产品
    @Select("select * from product where id=#{id}")
    public Product findByProductId(String id) throws Exception;
}
