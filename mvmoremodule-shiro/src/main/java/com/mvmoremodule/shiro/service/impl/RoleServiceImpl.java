package com.mvmoremodule.shiro.service.impl;/**
 * Created by Administrator on 2018-05-23.
 */

import com.mvmoremodule.shiro.dao.RoleMapper;
import com.mvmoremodule.shiro.pojo.SysRole;
import com.mvmoremodule.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-05-23-9:18
 */
@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<SysRole> selectRoleByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }
}
