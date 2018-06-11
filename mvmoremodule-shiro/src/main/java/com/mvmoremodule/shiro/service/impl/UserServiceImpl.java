package com.mvmoremodule.shiro.service.impl;/**
 * Created by Administrator on 2018-05-23.
 */


import com.mvmoremodule.shiro.dao.RoleMapper;
import com.mvmoremodule.shiro.dao.SysUserRoleMapper;
import com.mvmoremodule.shiro.dao.UserMapper;
import com.mvmoremodule.shiro.pojo.SysUser;
import com.mvmoremodule.shiro.pojo.SysUserRole;
import com.mvmoremodule.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-05-23-9:10
 */
@Service()
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUser selectByid(Long id) {
        return userMapper.selectByid(id);
    }

    @Override
    public List<SysUser> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<SysUser> selectByIds(List<Long> ids) {
        return userMapper.selectByIds(ids);
    }

    @Override
    public SysUser selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public SysUser selectUserAndRoleById(Long id) {
        return userMapper.selectUserAndRoleById(id);
    }

    @Override
    public int addSysUser(SysUser sysUser) {
        return userMapper.addSysUser(sysUser);
    }

    @Override
    public int updateSysUser(SysUser sysUser) {
        return userMapper.updateSysUser(sysUser);
    }

    @Override
    public int deleteByUserId(Long id) {
        return userMapper.deleteByUserId(id);
    }

    @Override
    public int updateSysUserInfoAndName(Long id, String userInfo, String userName) {
        return userMapper.updateSysUserInfoAndName(id,userInfo,userName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int addSysUserAndSetRole(SysUser sysUser, Long roleId) {
        int flag = userMapper.addSysUser(sysUser);
        if(flag > 0){
            Long userId = sysUser.getId();
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(userId);
            sysUserRole.setUserId(roleId);
            flag = sysUserRoleMapper.addSysUserRole(sysUserRole);
        }
        return flag;
    }
}
