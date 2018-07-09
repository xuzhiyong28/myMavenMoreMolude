package com;/**
 * Created by Administrator on 2018-06-02.
 */

import com.github.pagehelper.PageHelper;
import com.mvmoremodule.shiro.dao.SysPrivilegeMapper;
import com.mvmoremodule.shiro.pojo.LearnResouce;
import com.mvmoremodule.shiro.service.LearnService;
import com.mvmoremodule.shiro.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-06-02-18:08
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MybatisTest {

    @Autowired
    private LearnService learnService;
    @Autowired
    private UserService userService;

    @Autowired
    private SysPrivilegeMapper sysPrivilegeMapper;

    @Test
    public void test(){
        String algorithmName = "md5";
        String username = "xuzhiyong";
        String password = "123456";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, password,
                salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println(encodedPassword);
        System.out.println(salt2);
    }

    @Test
    public void test2(){
        /*PageHelper.startPage(1, 10);
        List<LearnResouce> list = learnService.queryLearnResouceListByAll();
        System.out.println(list.size());*/
        List<Long> ids = new ArrayList<Long>();
        ids.add(1l);
        System.out.println(sysPrivilegeMapper.querySysPrivilegeByRoleIds(ids).size() + "...");
    }
}
