package com.xzy.tx.impl;

import com.xzy.tx.TxOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Component
@Transactional
public class TxOtherServiceImpl implements TxOtherService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBook() {
        jdbcTemplate.update("INSERT INTO `book`( `book`, `price`) VALUES (?,?)","书",12);
    }
}
