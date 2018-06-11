package com.mvmoremodule.shiro.controller;/**
 * Created by Administrator on 2018-06-02.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuzhiyong
 * @createDate 2018-06-02-21:10
 */
@Controller
@RequestMapping("/shiro")
public class ShiroController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
