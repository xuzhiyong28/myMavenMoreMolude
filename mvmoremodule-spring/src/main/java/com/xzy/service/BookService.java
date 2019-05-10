package com.xzy.service;

import com.xzy.dao.BookDAO;
import com.xzy.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BookService {

    //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 手动回滚事务

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private StudentService studentService;

    public Book initBook(){
        Book book = new Book();
        book.setPrice(122);
        book.setPublishDate(new Date());
        book.setTitle("书");
        return book;
    }


    public void test1(){
        // 1 / 0 发生异常 这里不做处理 aop会自动回滚
        studentService.update();
        bookDAO.add(initBook());
        int i = 1 / 0 ;
    }


    public void test2(){
        //发生异常时手动catch后事务不能回滚
        try{
            studentService.update();
            bookDAO.add(initBook());
            int i = 1 / 0 ;
        }catch (Exception e){
            System.out.println("手动处理异常");
        }
    }

    public void test3(){
        //捕获异常后但是又抛出这样事务能回滚
        try{
            studentService.update();
            bookDAO.add(initBook());
            int i = 1 / 0 ;
        }catch (Exception e){
            System.out.println("手动处理异常");
            throw new RuntimeException("抛出"); //默认情况下对Error和RuntimeException及其子类进行回滚
        }
    }

    public void test4(){
        //手动回滚事务，这样即使做了异常处理我们手动可以进行回滚
        try{
            studentService.update();
            bookDAO.add(initBook());
            int i = 1 / 0 ;
        }catch (Exception e){
            System.out.println("手动处理异常");
            System.out.println("手动回滚事务");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //手动回滚事务
        }
    }



}