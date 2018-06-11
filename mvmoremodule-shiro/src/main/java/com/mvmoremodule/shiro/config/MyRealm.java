package com.mvmoremodule.shiro.config;/**
 * Created by Administrator on 2018-06-03.
 */

import com.mvmoremodule.shiro.pojo.SysPrivilege;
import com.mvmoremodule.shiro.pojo.SysRole;
import com.mvmoremodule.shiro.pojo.SysUser;
import com.mvmoremodule.shiro.service.RoleService;
import com.mvmoremodule.shiro.service.SysPrivilegeService;
import com.mvmoremodule.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-06-03-8:19
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SysPrivilegeService sysPrivilegeService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = (SysUser)principalCollection.getPrimaryPrincipal();
        List<String> permissionList = new ArrayList<String>();
        List<Long> roldIds = new ArrayList<Long>();
        if(sysUser != null && sysUser.getRoleList() != null){
            List<SysRole> roleList = sysUser.getRoleList();
            for(SysRole sysRole  : roleList)
                roldIds.add(sysRole.getId());
            List<SysPrivilege> sysPrivilegeList = sysPrivilegeService.querySysPrivilegeByRoleIds(roldIds);
            if(sysPrivilegeList != null && sysPrivilegeList.size() > 0){
                for(SysPrivilege sysPrivilege : sysPrivilegeList)
                    permissionList.add(sysPrivilege.getPrivilegeName());
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal(); //得到用户名
        String salt2 = "3d60142deea228f8d097ab9ac0f3302f"; //模拟从数据库获取的盐

        SysUser sysUser = userService.selectByUserName(username);
        if(sysUser == null){
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!username.equals(sysUser.getUserName())){
            throw new UnknownAccountException(); //如果用户名错误
        }

        List<SysRole> roleList = roleService.selectRoleByUserId(sysUser.getId());
        if(roleList != null){
            sysUser.setRoleList(roleList);
        }


        //如果身份认证验证成功，返回一个AuthenticationInfo实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser, // 用户名
                sysUser.getUserPassword(), // 密码
                ByteSource.Util.bytes(sysUser.getUserName() + salt2),
                getName() // realmName
        );
        return authenticationInfo;
    }



    //清理授权缓存
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }


    //清理认证缓存
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    //清理所有缓存
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
