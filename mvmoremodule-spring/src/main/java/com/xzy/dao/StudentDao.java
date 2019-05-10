package com.xzy.dao;

import com.xzy.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {

    public List<Student> getAllStudent();

    public Student getStudentById(@Param("id") int id);

    public int add(Student entity);

    public int delete(int id);

    public int update(Student entity);
}
