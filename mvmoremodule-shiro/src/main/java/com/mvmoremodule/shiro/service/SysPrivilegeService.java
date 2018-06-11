package com.mvmoremodule.shiro.service;

import com.mvmoremodule.shiro.pojo.SysPrivilege;

import java.util.List;

/**
 * Created by Administrator on 2018-06-08.
 */
public interface SysPrivilegeService {
    public List<SysPrivilege> querySysPrivilegeAll();
    public List<SysPrivilege> querySysPrivilegeByRoleIds(List<Long> roleId);
}
