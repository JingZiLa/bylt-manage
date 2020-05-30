package com.mirror.dao;

import com.mirror.domain.Role;
import com.mirror.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 * 操作用户Dao
 */
@Repository
public interface UserDao {
    //根据用户姓名查询用户信息
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles" ,column = "id",javaType = java.util.List.class, many = @Many(select = "com.mirror.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    //查询所有用户信息
    @Select("select * from users")
    public List<UserInfo> findAll()throws Exception;

    //保存用户
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    //根据用户ID查询用户具体信息
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id", javaType = java.util.List.class,many = @Many(select = "com.mirror.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws Exception;


    //根据用户ID查询用户所有未添加的角色
    @Select("select * from role where id not in (select roleId from users_role where userId=#{id})")
    List<Role> findOtherRoles(String id)throws Exception;

    //根据用户ID添加角色
    @Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId)throws Exception;
}
