package com.xzy.controller;

import com.xzy.model.UiModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
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



    @RequestMapping("/requestBody.action")
    public void requestBody(@RequestBody String body, Writer writer) throws IOException {
        writer.write(body);
    }

    @RequestMapping(value="/responseBody.action", produces="application/json")
    @ResponseBody
    public Map<String, Object> responseBody(){
        Map<String, Object> retMap = new HashMap<String,Object>();
        retMap.put("param1", "abc");
        return retMap;
    }




}
