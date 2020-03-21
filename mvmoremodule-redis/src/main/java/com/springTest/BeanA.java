package com.springTest;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author xuzhiyong
 * @createDate 2019-12-12-22:52
 */
@Component
public class BeanA implements Serializable {
    private String fieldOne;

    public BeanA(){

    }

    public BeanA(String fieldOne){
        this.fieldOne = fieldOne;
    }

    public String getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
    }
}
