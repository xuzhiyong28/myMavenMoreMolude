package xzy.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.Map;

public class JWTUtil {
    public static String SECRET = "JKKLJOoasdlfj";
    /** token 过期时间: 30分钟 */
    // 过期时间30分钟
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    /***
     * 创建token
     * @param user_id
     * @return
     * @throws Exception
     */
    public static String createToken(Long user_id) throws Exception {
        Date iatDate = new Date();
        Date expiresDate  = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Map<String, Object> headMap = Maps.newHashMap();
        headMap.put("alg", "HS256");
        headMap.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(headMap)
                .withClaim("iss", "Service")
                .withClaim("aud", "APP")
                .withClaim("user_id", user_id.toString())
                .withIssuedAt(iatDate)
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /***
     * 解密
     * @param token
     * @return
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jwt.getClaims();
    }

}
