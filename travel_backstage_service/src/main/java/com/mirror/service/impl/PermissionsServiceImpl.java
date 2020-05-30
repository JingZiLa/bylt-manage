package com.mirror.service.impl;

import com.github.pagehelper.PageHelper;
import com.mirror.dao.PermissionsDao;
import com.mirror.domain.Permission;
import com.mirror.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 * 资源权限业务处理层
 */
@Service("PermissionsService")
@Transactional
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsDao permissionsDao;

    /**
     * 业务：查询所有资源权限
     * @param page
     * @param pagesize
     * @return 资源权限集合
     * @throws Exception
     */
    @Override
    public List<Permission> findAll(int page, int pagesize) throws Exception {
        PageHelper.startPage(page,pagesize);
        return permissionsDao.findAll();
    }

    /**
     *业务：根据id查询权限具体信息
     * @return  权限具体信息
     */
    @Override
    public Permission findById(String id) throws Exception{
        return permissionsDao.findById(id);
    }

    /**
     * 业务：
     *      根据id删除与角色表关联的外键
     *      根据信息删除权限
     * @param id
     */
    @Override
    public void deletePermission(String id) throws Exception{

        permissionsDao.deleteFromRole_PermissionByPermissionId(id);
        permissionsDao.deletePermissionById(id);
    }

    /**
     * 业务：保存资源权限
     * @param permission
     */
    @Override
    public void savePermission(Permission permission) throws Exception{
        permissionsDao.save(permission);
    }
}
