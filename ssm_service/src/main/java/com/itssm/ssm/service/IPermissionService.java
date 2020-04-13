package com.itssm.ssm.service;

import com.itssm.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll()throws Exception;

    void save(Permission permission) throws Exception;
}
