package xzy.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

public class JwtTest {

    // 过期时间30分钟
    public static final long EXPIRE_TIME = 30 * 60 * 1000;
    public static final String USERNAME = "xuzy";
    public static final String PASSWORD = "xuzhiyong@123456";
    /***
     * 加密测试
     */
    @Test
    public void signTest(){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(PASSWORD);
        String sign = JWT.create().withClaim("username",USERNAME).withExpiresAt(date).sign(algorithm);
        System.out.println(sign);
    }


    /***
     * 解密校验
     */
    @Test
    public void verify(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1NDEyNDIsInVzZXJuYW1lIjoieHV6eSJ9.dvKxDGwQwhS2t9GQomxjDpwmSLEPexh5vYiwl-rkZek";
        Algorithm algorithm = Algorithm.HMAC256(PASSWORD);
        JWTVerifier verifier = JWT.require(algorithm).withClaim("username", USERNAME).build();
        try{
            DecodedJWT jwt = verifier.verify(token);
        }catch (Exception e){
            System.out.println("校验错误");
        }
    }



    @Test
    public void test1() throws Exception {
        String token = JWTUtil.createToken(10000L);
        System.out.println(token);
        Map<String, Claim> stringClaimMap = JWTUtil.verifyToken(token);
        System.out.println(stringClaimMap);
    }







}
