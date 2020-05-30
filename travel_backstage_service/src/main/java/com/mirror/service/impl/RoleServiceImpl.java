package com.mirror.service.impl;

import com.github.pagehelper.PageHelper;
import com.mirror.dao.RoleDao;
import com.mirror.domain.Permission;
import com.mirror.domain.Role;
import com.mirror.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 * 角色业务层
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色(分页)
     *
     * @param page
     * @param pagesize
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findAll(int page, int pagesize) throws Exception {
        PageHelper.startPage(page, pagesize);
        return roleDao.findAll();
    }

    /**
     * 业务：保存角色信息
     *
     * @param role
     * @throws Exception
     */
    @Override
    public void saveRole(Role role) throws Exception {
        roleDao.save(role);
    }

    /**
     * 业务：查询角色具体信息
     *
     * @param id
     * @return 角色信息 ROLE
     */
    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    /**
     * 业务：
     * 删除角色与用户中间表外键
     * 删除角色与资源权限中间表外键
     * 删除角色表角色信息
     *
     * @param roleId
     */
    @Override
    public void deleteRoleById(String roleId) throws Exception {
        //从user_role表中删除
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        roleDao.deleteRoleById(roleId);
    }

    /**
     * 业务：根据角色ID查询未添加的权限
     *
     * @param id
     * @return 未添加的权限集合
     * @throws Exception
     */
    @Override
    public List<Permission> findOtherPermissions(String id) throws Exception {
        return roleDao.findOtherPermissions(id);
    }

    /**
     * 根据角色ID添加资源权限
     *
     * @param roleId
     * @param permissionIds
     * @throws Exception
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds
        ) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
