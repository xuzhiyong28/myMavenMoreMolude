package com.xzy;

import com.xzy.service.BookService;
import com.xzy.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xuzhiyong
 * @createDate 2019-05-09-22:14
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MybatisTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentService studentService;

    @Test
    public void test(){
        bookService.test1();
    }

    @Test
    public void test2(){
        bookService.test2();
    }

    @Test
    public void test3(){
        bookService.test3();
    }

    @Test
    public void test4(){
        bookService.test4();
    }

    @Test
    public void test5(){
        bookService.test5();
    }
}
