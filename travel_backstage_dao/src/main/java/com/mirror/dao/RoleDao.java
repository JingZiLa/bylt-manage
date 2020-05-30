package com.mirror.dao;

import com.mirror.domain.Permission;
import com.mirror.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 * 操作角色Dao
 */
@Repository
public interface RoleDao {

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id" ,javaType = java.util.List.class,many = @Many(select = "com.mirror.dao.PermissionsDao.findPermissionByRoleId"))
    })
    Role findRoleByUserId(String userid)throws Exception;

    //查询所有角色
    @Select("select * from role")
    List<Role> findAll()throws Exception;

    //保存角色
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    //根据id查询角色具体信息
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.mirror.dao.PermissionsDao.findPermissionByRoleId"))
    })
    Role findById(String id)throws Exception;

    //从user_role表中删除
    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId)throws Exception;


    //从role_permission表中删除
   @Delete("delete from role_permission where roleId =#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId)throws Exception;

   @Delete("delete from role where id=#{roleId}")
    //从role表中删除
    void deleteRoleById(String roleId)throws Exception;

    //根据权限id查询出所有对应的角色
    @Select("select * from role where id in(select permissionId from role_permission where permissionId=#{permissionId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
    })
    Role findRoleByPermissionsId(String permissionId)throws Exception;

    //根据角色ID查询未添加的权限
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermissions(String id);

    //根据角色ID添加资源权限
    @Insert("insert into role_permission (permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
