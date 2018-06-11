package com.mvmoremodule.shiro.pojo;/**
 * Created by Administrator on 2018-05-16.
 */

import java.util.Date;

/**
 * @author xuzhiyong
 * @createDate 2018-05-16-14:23
 */
public class SysRole {
    private Long id;
    private String roleName;
    private String enabled;
    private Long createBy;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
