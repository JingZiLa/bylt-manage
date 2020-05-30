package com.mirror.service;

import com.mirror.domain.Permission;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 * 资源权限业务接口
 */
public interface PermissionsService {

    List<Permission> findAll(int page, int pagesize)throws Exception;

    Permission findById(String id) throws Exception;

    void deletePermission(String id)throws Exception;

    void savePermission(Permission permission)throws Exception;
}
