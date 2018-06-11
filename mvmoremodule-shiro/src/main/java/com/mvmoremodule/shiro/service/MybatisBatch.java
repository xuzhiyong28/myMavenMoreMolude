package com.mvmoremodule.shiro.service;


import com.mvmoremodule.shiro.pojo.LearnResouce;

import java.util.List;

/**
 * Created by Administrator on 2018-05-27.
 */
public interface MybatisBatch {
    public boolean addUserBatch(List<LearnResouce> lrs);
}
