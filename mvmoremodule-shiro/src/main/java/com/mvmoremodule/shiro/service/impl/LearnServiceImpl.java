package com.mvmoremodule.shiro.service.impl;/**
 * Created by Administrator on 2018-04-30.
 */


import com.github.pagehelper.PageHelper;
import com.mvmoremodule.shiro.dao.LearnMapper;
import com.mvmoremodule.shiro.pojo.LearnResouce;
import com.mvmoremodule.shiro.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author xuzhiyong
 * @createDate 2018-04-30-20:14
 */
@Service("learnServiceImpl")
public class LearnServiceImpl implements LearnService {



    @Autowired
    LearnMapper learnMapper;


    @Override
    @Transactional
    public int add(LearnResouce learnResouce) {
        return this.learnMapper.add(learnResouce);
    }

    @Override
    public int update(LearnResouce learnResouce) {
        return this.learnMapper.update(learnResouce);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.learnMapper.deleteByIds(ids);
    }

    @Override
    public LearnResouce queryLearnResouceById(Long id) {
        return this.learnMapper.queryLearnResouceById(id);
    }

    @Override
    public List<LearnResouce> queryLearnResouceList(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.learnMapper.queryLearnResouceList(params);
    }

    @Override
    public List<LearnResouce> queryLearnResouceListByAll() {
        /*boolean flag = transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                LearnResouce learnResouce = new LearnResouce();
                learnResouce.setAuthor("许志勇");
                learnResouce.setTitle("测试标题");
                learnResouce.setUrl("http://localhost:8080");
                int flagInt = learnMapper.add(learnResouce);
                return flagInt == 1 ? true : false;
            }
        });*/
        return this.learnMapper.queryLearnResouceListByAll();
    }

}
