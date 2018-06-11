package com.mvmoremodule.shiro.dao;

import com.mvmoremodule.shiro.pojo.SysPrivilege;

import java.util.List;

/**
 * Created by Administrator on 2018-06-08.
 */
public interface SysPrivilegeMapper {
    public List<SysPrivilege> querySysPrivilegeAll();
    public List<SysPrivilege> querySysPrivilegeByRoleIds(List<Long> roleId);
}
