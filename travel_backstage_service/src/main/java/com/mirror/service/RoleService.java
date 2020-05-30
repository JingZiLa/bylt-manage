package com.mirror.service;

import com.mirror.domain.Permission;
import com.mirror.domain.Role;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 */
public interface RoleService {

    List<Role> findAll(int page,int pagesize) throws Exception;

    void saveRole(Role role) throws Exception;

    Role findById(String id)throws Exception;

    void deleteRoleById(String roleId)throws Exception;

    List<Permission> findOtherPermissions(String id)throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds)throws Exception;
}
