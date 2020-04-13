package com.itssm.ssm.service;

import com.itssm.ssm.domain.Role;
import com.itssm.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * UserDetailsService用户规范方法
 */
public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;


    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRoles(String userId);

    void addRoleToUser(String userId, String[] roleIds);
}
