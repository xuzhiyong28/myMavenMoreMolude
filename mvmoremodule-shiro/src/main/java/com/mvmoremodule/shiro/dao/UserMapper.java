package com.mvmoremodule.shiro.dao;/**
 * Created by Administrator on 2018-05-16.
 */

import com.mvmoremodule.shiro.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-05-16-11:45
 */
public interface UserMapper {
    public SysUser selectByid(Long id);
    public List<SysUser> selectAll();
    public List<SysUser> selectByIds(List<Long> ids);
    public SysUser selectByUserName(String userName);
    public SysUser selectUserAndRoleById(Long id);
    public int addSysUser(SysUser sysUser);
    public int updateSysUser(SysUser sysUser);
    public int deleteByUserId(Long id);
    public int updateSysUserInfoAndName(@Param("id") Long id, @Param("userInfo") String userInfo, @Param("userName") String userName);
}
