package com.mvmoremodule.shiro.pojo;/**
 * Created by Administrator on 2018-04-30.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-30-20:11
 */
public class LearnResouce {
    private Long id;
    private String author;
    private String title;
    private String url;

    private LearnResouceGroup group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public LearnResouceGroup getGroup() {
        return group;
    }

    public void setGroup(LearnResouceGroup group) {
        this.group = group;
    }
}
