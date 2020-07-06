package com.xzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @RequestMapping(value = "/demo1.action")
    @ResponseBody
    public Map demo1() {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("uuid", UUID.randomUUID().toString());
        return resultMap;
    }

    @RequestMapping(value = "/demo2.action")
    public String demoForward(String name, HttpServletRequest request) {
        request.setAttribute("name", name);
        return "forward:/demo/demo3.action";
    }

    @RequestMapping(value = "/demo3.action")
    public String demo3(){
        return "result";
    }
}
