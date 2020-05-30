package com.mirror.controller;

import com.github.pagehelper.PageInfo;
import com.mirror.domain.Permission;
import com.mirror.domain.Role;
import com.mirror.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 * 角色Controller
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     *
     * @return
     */
    @GetMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pagesize", required = true, defaultValue = "5") Integer pagesize) throws Exception {
        ModelAndView mv = new ModelAndView();

        List<Role> roleList = roleService.findAll(page, pagesize);
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 保存角色信息
     *
     * @param role
     * @return 查询所有地址
     * @throws Exception
     */
    @PostMapping("/saveRole.do")
    @RolesAllowed("ADMIN")
    public String saveRole(Role role) throws Exception {

        roleService.saveRole(role);
        return "redirect:findAll.do";
    }

    /**
     * 查询角色具体信息
     * @param id
     * @return  角色信息and视图
     */
    @GetMapping("/findById.do")
    public ModelAndView fandById(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
            Role role = roleService.findById(id);
            mv.addObject("role",role);
            mv.setViewName("role-show");
        return mv;
    }

    /**
     * 删除角色
     * @param roleId
     * @return 查询所有地址
     * @throws Exception
     */
    @RequestMapping("/deleteRole.do")
    @RolesAllowed("ADMIN")
    public String deleteRole(@RequestParam(name="id",required = true) String roleId) throws Exception {
        roleService.deleteRoleById(roleId);
        return "redirect:findAll.do";
    }

    /**
     * 根据角色ID查询角色信息And查询角色未添加的资源权限
     * @param id
     * @return  角色信息And角色未添加资源权限合
     * @throws Exception
     */
    @GetMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        List<Permission> list = roleService.findOtherPermissions(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",list);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /**
     * 根据角色ID添加资源权限
     * @param roleId
     * @param permissionIds
     * @throws Exception
     */
    @PostMapping("/addPermissionToRole.do")
    @RolesAllowed("ADMIN")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true)String roleId,@RequestParam(name = "permissionIds", required = true)String[] permissionIds)throws Exception{
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
