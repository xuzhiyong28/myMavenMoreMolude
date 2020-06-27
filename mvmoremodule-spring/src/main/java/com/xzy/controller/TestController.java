package com.xzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class TestController {

    @RequestMapping(value = "/test.action")
    @ResponseBody
    public Map send(HttpServletRequest request) {
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("uuid", UUID.randomUUID().toString());
        return resultMap;
    }

}
