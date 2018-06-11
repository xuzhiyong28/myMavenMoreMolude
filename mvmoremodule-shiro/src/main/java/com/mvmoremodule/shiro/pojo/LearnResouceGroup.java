package com.mvmoremodule.shiro.pojo;/**
 * Created by Administrator on 2018-05-16.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-16-16:28
 */
public class LearnResouceGroup {
    private Long id;
    private String groupName;
    private String groupInfo;

    @Override
    public String toString() {
        return "LearnResouceGroup{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupInfo='" + groupInfo + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }
}
