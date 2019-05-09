package com.xzy.service;

import com.xzy.dao.BookDAO;
import com.xzy.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    public void test(){
        List<Book> list =  bookDAO.getAllBooks();
        System.out.println(list);
    }

    public void add1(){
        Book book = new Book();
        book.setPrice(123);
        book.setPublishDate(new Date());
        book.setTitle("xuzy");
        bookDAO.add(book);
        int i = 1 / 0;
    }


    public void add2(){
        try{
            Book book = new Book();
            book.setPrice(123);
            book.setPublishDate(new Date());
            book.setTitle("xuzy");
            bookDAO.add(book);
            int i = 1 / 0;
        }catch (Exception e){
            System.out.println("做异常处理!!!");
        }
    }

    public void add3() throws RuntimeException{
        try{
            Book book = new Book();
            book.setPrice(123);
            book.setPublishDate(new Date());
            book.setTitle("xuzy");
            bookDAO.add(book);
            int i = 1 / 0;
        }catch (Exception e){
            System.out.println("做异常处理!!!");
            throw new RuntimeException("异常抛出");
        }
    }

    public void add4(){
        try{
            Book book = new Book();
            book.setPrice(123);
            book.setPublishDate(new Date());
            book.setTitle("xuzy");
            bookDAO.add(book);
            int i = 1 / 0;
        }catch (Exception e){
            System.out.println("做异常处理!!!");
            //手动回滚事物
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }


}
