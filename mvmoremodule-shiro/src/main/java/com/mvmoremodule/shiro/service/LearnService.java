package com.mvmoremodule.shiro.service;


import com.mvmoremodule.shiro.pojo.LearnResouce;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018-04-30.
 */
public interface LearnService {
    int add(LearnResouce learnResouce);
    int update(LearnResouce learnResouce);
    int deleteByIds(String[] ids);
    LearnResouce queryLearnResouceById(Long learnResouce);
    List<LearnResouce> queryLearnResouceList(Map<String, Object> params);
    public List<LearnResouce> queryLearnResouceListByAll();

}
