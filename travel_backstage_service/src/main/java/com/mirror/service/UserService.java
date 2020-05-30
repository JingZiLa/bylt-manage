package com.mirror.service;

import com.mirror.domain.Role;
import com.mirror.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author Mirror
 * @CreateDate 2020/3/2.
 */
public interface UserService extends UserDetailsService {


    List<UserInfo> findAll(int page ,int pagesize) throws Exception;

    void saveUser(UserInfo user) throws Exception;

    UserInfo findById(String id)throws Exception;

    List<Role> findOtherRoles(String id)throws Exception;

    void addRoleToUser(String userId, String[] roleIds)throws Exception;
}
