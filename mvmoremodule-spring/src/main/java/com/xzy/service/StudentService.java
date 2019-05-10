package com.xzy.service;

import com.xzy.dao.StudentDao;
import com.xzy.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(propagation = Propagation.REQUIRED)
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public List<Student> list(){
        List<Student> list = studentDao.getAllStudent();
        System.out.println(list);
        return list;
    }

    public void update(){
        Student student = new Student();
        student.setId(1);
        student.setName("xuzy_" + System.currentTimeMillis());
        studentDao.update(student);
    }
}
