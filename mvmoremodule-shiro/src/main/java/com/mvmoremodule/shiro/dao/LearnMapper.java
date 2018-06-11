package com.mvmoremodule.shiro.dao;


import com.mvmoremodule.shiro.pojo.LearnResouce;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018-04-30.
 */
public interface LearnMapper {
    int add(LearnResouce learnResouce);
    int addByBatch(List<LearnResouce> learnResouceList);
    int update(LearnResouce learnResouce);
    int deleteByIds(String[] ids);
    LearnResouce queryLearnResouceById(Long id);
    public List<LearnResouce> queryLearnResouceList(Map<String, Object> params);
    public List<LearnResouce> queryLearnResouceListByAll();
    public List<LearnResouce> queryLearnResouceListByExtendAll();
    public List<LearnResouce> queryLearnResouceListByAssociationAll();
    public LearnResouce queryLearnResouceListByAssociationSelect(Long id);
}
