package com.mirror.controller;

import com.github.pagehelper.PageInfo;
import com.mirror.domain.Permission;
import com.mirror.service.PermissionsService;
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
 * 资源权限控制层
 */
@Controller
@RequestMapping("/permission")
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;


    /**
     * 查询所有资源权限方法
     *
     * @param page
     * @param pagesize
     * @return 资源权限集合And视图
     * @throws Exception
     */
    @GetMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pagesize", required = true, defaultValue = "5") Integer pagesize) throws Exception {
        ModelAndView mv = new ModelAndView();

        List<Permission> list = permissionsService.findAll(page, pagesize);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    /**
     * 根据ID 查询资源权限信息
     *
     * @param id
     * @return 资源权限信息And视图
     */
    @GetMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionsService.findById(id);
        mv.addObject("permission", permission);
        mv.setViewName("permission-show");
        return mv;
    }

    /**
     * 根据id删除权限信息
     *
     * @param id
     * @return 查询所有资源权限请求
     */
    @GetMapping("/deletePermission.do")
    @RolesAllowed("ADMIN,MIRROR")
    public String deletePermission(@RequestParam(name = "id", required = true) String id) throws Exception {
        permissionsService.deletePermission(id);
        return "redirect:findAll.do";
    }

    /**
     * 保存资源权限
     * @param permission
     * @return  查询所有资源权限请求
     */
    @PostMapping("/savePermission.do")
    @RolesAllowed("MIRROR,ADMIN")
    public String savePermission(Permission permission) throws Exception {
        permissionsService.savePermission(permission);
        return "redirect:findAll.do";
    }
}
