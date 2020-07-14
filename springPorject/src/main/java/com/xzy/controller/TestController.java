package com.xzy.controller;

import com.xzy.model.UiModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("uuid", UUID.randomUUID().toString());
        return resultMap;
    }

    @RequestMapping(value = "/test2.action")
    @ResponseBody
    public UiModel sendUiModel(UiModel uiModel){
        System.out.println(uiModel);
        return uiModel;
    }


    @RequestMapping(value = "/result.action")
     public String result(String name,HttpServletRequest request){
        System.out.println("==========result=========");
        System.out.println(name);
        return "result";
     }

}
