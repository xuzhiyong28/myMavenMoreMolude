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
//@Transactional(propagation = Propagation.REQUIRED)
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
        studentService.update();
        bookDAO.add(initBook());
        int i = 1 / 0 ;
    }



}
