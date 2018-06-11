package com.mvmoremodule.shiro.controller;/**
 * Created by Administrator on 2018-06-06.
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author xuzhiyong
 * @createDate 2018-06-06-16:45
 */
@Controller
public class MainController {
    @RequestMapping("/")
    @RequiresPermissions("user:index")
    public String index(Model model) {
        return "index";
    }


    @RequestMapping("/add")
    @RequiresPermissions("user:add")
    public String add(HttpServletRequest request){
        return "add";
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request) {
        String loginName = request.getParameter("username");
        String loginPassword = request.getParameter("password");
        HttpSession session = request.getSession(true);
        String errorMessage = "";
        Subject user = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPassword);
        token.setRememberMe(false);
        try {
            user.login(token);
            //String userID = (String) user.getPrincipal();
            //session.setAttribute("USERNAME", userID);
            return "success";
        } catch (UnknownAccountException uae) {
            errorMessage = "用户认证失败：" + "username wasn't in the system.";
        } catch (IncorrectCredentialsException ice) {
            errorMessage = "用户认证失败：" + "password didn't match.";
        } catch (LockedAccountException lae) {
            errorMessage = "用户认证失败：" + "account for that username is locked - can't login.";
        }catch (ExcessiveAttemptsException eae){
            errorMessage = "用户认证失败：密码登录次数超过5次";
        } catch (AuthenticationException e) {
            errorMessage = "登录失败错误信息：" + e;
            e.printStackTrace();
            token.clear();
        }
        session.setAttribute("ErrorMessage", errorMessage);
        return "error";
    }

    @RequestMapping(value = "/success")
    public String success(HttpServletRequest request){
        return  "success";
    }

    @RequestMapping(value = "/unauthorized")
    public String unauthorized(HttpServletRequest request){
        return "unauthorized";
    }

}
