package com.itssm.ssm.service;

import com.itssm.ssm.domain.Permission;
import com.itssm.ssm.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRoleService {
    List<Role> findAll() throws Exception;

    void save(Role role);

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds);
}
