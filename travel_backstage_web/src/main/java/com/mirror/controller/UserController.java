package com.mirror.controller;

import com.github.pagehelper.PageInfo;
import com.mirror.domain.Role;
import com.mirror.domain.UserInfo;
import com.mirror.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/3.
 * 用户Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户信息(分页)
     * @param page
     * @param pagesize
     * @return
     * @throws Exception
     */
    @GetMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pagesize", required = true, defaultValue = "5") Integer pagesize) throws Exception{
        ModelAndView mv = new ModelAndView();

        List<UserInfo> userList =userService.findAll(page,pagesize);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @PostMapping("/saveUser.do")
    @RolesAllowed("ADMIN")
    public String saveUser(UserInfo user) throws Exception {
        userService.saveUser(user);
        return "redirect:findAll.do";
    }

    /**
     * 根据用户ID查询用户具体信息
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true )String id)throws Exception{
       ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 根据用户ID查询用户信息And查询用户未添加的角色
     * @param id
     * @return  用户信息And用户未添加角色集合
     * @throws Exception
     */
    @GetMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        List<Role> list = userService.findOtherRoles(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",list);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 根据用户ID添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    @PostMapping("/addRoleToUser.do")
    @RolesAllowed("ADMIN")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "roleIds", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
