package com.xzy.tx.impl;

import com.xzy.tx.TxOtherService;
import com.xzy.tx.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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
}
