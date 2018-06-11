package com.mvmoremodule.shiro.dao;/**
 * Created by Administrator on 2018-05-16.
 */


import com.mvmoremodule.shiro.pojo.SysRole;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-05-16-14:31
 */
public interface RoleMapper {
    public List<SysRole> selectRoleByUserId(Long userId);
}
