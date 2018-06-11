package com.mvmoremodule.shiro.service.impl;/**
 * Created by Administrator on 2018-06-08.
 */

import com.mvmoremodule.shiro.dao.SysPrivilegeMapper;
import com.mvmoremodule.shiro.pojo.SysPrivilege;
import com.mvmoremodule.shiro.service.SysPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-06-08-10:09
 */
@Service
public class SysPrivilegeServiceImpl implements SysPrivilegeService {
    @Autowired
    private SysPrivilegeMapper sysPrivilegeMapper;
    @Override
    public List<SysPrivilege> querySysPrivilegeAll() {
        return sysPrivilegeMapper.querySysPrivilegeAll();
    }

    @Override
    public List<SysPrivilege> querySysPrivilegeByRoleIds(List<Long> roleId) {
        return sysPrivilegeMapper.querySysPrivilegeByRoleIds(roleId);
    }
}
