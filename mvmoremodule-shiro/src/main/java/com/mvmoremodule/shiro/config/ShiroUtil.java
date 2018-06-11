package com.mvmoremodule.shiro.config;/**
 * Created by Administrator on 2018-06-08.
 */

import com.mvmoremodule.shiro.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;


/**
 * @author xuzhiyong
 * @createDate 2018-06-08-22:03
 */
public class ShiroUtil {
    public static SysUser getSysUser(){
        try{
            Subject subject = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) subject.getPrincipal();
            if(sysUser != null)
                return sysUser;
        }catch (UnavailableSecurityManagerException e){
            e.printStackTrace();
        }
        return null;
    }
}
