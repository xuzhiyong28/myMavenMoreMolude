package com.mvmoremodule.shiro.config;/**
 * Created by Administrator on 2018-06-07.
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author xuzhiyong
 * @createDate 2018-06-07-9:14
 */
public class PassWordUtil {
    /***
     * md5 三次散列加密
     * @param userName
     * @param password
     * @return
     */
    public static JSONObject encryByMd5(String userName,String password){
        JSONObject result = new JSONObject();
        String algorithmName = "md5";
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        result.put("salt2",salt2);
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, password,
                userName + salt2, hashIterations);
        result.put("encodedPassword",hash.toHex());
        return result;
    }
}
