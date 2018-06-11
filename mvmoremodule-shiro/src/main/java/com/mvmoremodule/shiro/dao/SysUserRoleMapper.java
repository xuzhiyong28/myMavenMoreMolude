package com.mvmoremodule.shiro.dao;/**
 * Created by Administrator on 2018-05-23.
 */


import com.mvmoremodule.shiro.pojo.SysUserRole;

import java.util.List;
import java.util.Map;

/**
 * @author xuzhiyong
 * @createDate 2018-05-23-9:35
 */
public interface SysUserRoleMapper {
    public List<SysUserRole> queryByUserId(Long userId);
    public int addSysUserRole(SysUserRole sysUserRole);
    public List<SysUserRole> queryByUserIdAndRoleId(Map map);
}
