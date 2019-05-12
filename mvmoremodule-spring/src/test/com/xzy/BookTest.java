package com.xzy;

import com.xzy.service.BookService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xuzhiyong
 * @createDate 2019-05-12-13:05
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BookTest {
    @Autowired
    private BookService bookService;

    public void test1(){
        bookService.update();
    }
}
