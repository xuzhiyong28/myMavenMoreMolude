package com.xzy.tx.impl;

import com.xzy.tx.TxOtherService;
import com.xzy.tx.TxService;
import com.xzy.util.SpringUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Transactional
public class TxServiceImpl implements TxService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TxOtherService txOtherService;

    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryUser() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        return maps;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateData() {
        jdbcTemplate.update("INSERT INTO `user`( `name`, `age`) VALUES (?,?)", "名字1", 12);
        txOtherService.updateBook();

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void singleUpdate() {
        jdbcTemplate.update("INSERT INTO `user`( `name`, `age`) VALUES (?,?)", "名字Thread", 12);
        int i = 1 / 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateByThread(){
        jdbcTemplate.update("INSERT INTO `user`( `name`, `age`) VALUES (?,?)", "名字", 12);
        ExecutorService executors = Executors.newFixedThreadPool(2);
        executors.execute(new Runnable() {
            public void run() {
                TxService txService = SpringUtil.getBean(TxService.class);
                txService.singleUpdate();
            }
        });
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
