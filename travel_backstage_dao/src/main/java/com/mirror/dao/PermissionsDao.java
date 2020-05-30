package com.mirror.dao;

import com.mirror.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 * 资源权限Dao
 */
@Repository
public interface PermissionsDao {
    //查询与role关联的所有的权限
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    List<Permission> findPermissionByRoleId(String id)throws Exception;


    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    @Select("select * from permission where id=#{id}")
    @Results({
            @Result(id = true,column = "id"),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.mirror.dao.RoleDao.findRoleByPermissionsId"))
    })
    Permission findById(String id) throws Exception;

    //根据id删除与角色关联的中间表的权限ID
    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_PermissionByPermissionId(String id)throws Exception;

    //根据ID删除资源权限表信息
    @Delete("delete from permission where id = #{id}")
    void deletePermissionById(String id)throws Exception;

    //保存资源权限信息
    @Insert("insert into permission (permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission)throws Exception;
}
