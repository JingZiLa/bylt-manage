package com.mirror.service.impl;

import com.github.pagehelper.PageHelper;
import com.mirror.dao.UserDao;

import com.mirror.domain.Role;
import com.mirror.domain.UserInfo;
import com.mirror.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/2.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bpe;

    /**
     * 用户登陆认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo =null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
                User users = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 1 ? true:false,true,true,true, (Collection<? extends GrantedAuthority>) getAuthority(userInfo.getRoles()));
        return users;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }




    /**
     * 查询所有用户(分页)
     * @return 用户信息集合
     */
    @Override
    public List<UserInfo> findAll(int page, int pagesize)throws Exception{
        PageHelper.startPage(page,pagesize);
        List<UserInfo> userInfos = userDao.findAll();
        return userInfos;
    }

    /**
     * 保存用户
     * @param user
     */
    @Override
    public void saveUser(UserInfo user)throws Exception {
        user.setPassword(bpe.encode(user.getPassword()));
       userDao.save(user);
    }

    /**
     * 根据用户ID查询用户具体信息
     * @param id
     * @return 用户信息实体
     * @throws Exception
     */
    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }
    /**
     * 根据用户ID查询用户信息And查询用户未添加的角色
     * @param id
     * @return  用户未添加角色集合
     * @throws Exception
     */
    @Override
    public List<Role> findOtherRoles(String id) throws Exception {
        return userDao.findOtherRoles(id);
    }

    /**
     * 业务：根据用户ID添加角色
     * @param userId
     * @param roleIds
     * @throws Exception
     */
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId:roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
