package com.bing.rabbitmqtest.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

/**
 * jwt工具类
 * @author sunyibing
 * @date 2024/4/4
 */
public class JwtUtil {
    // 密钥
    public static final String TOKEN_SECRET = "token!@#951007";

    /**
     * 生成token
     * @param userId
     * @return
     */
    public static String createToken(String userId) {
        Calendar calendar = Calendar.getInstance();
        // 设置过期时间  30分钟
        calendar.add(Calendar.MINUTE, 30);
        return JWT.create().withClaim("userId", userId)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC384(TOKEN_SECRET));
    }

    /**
     * 校验token
     * @param token
     */
    public static String verifyToken(String token) {
        String userId;
        DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC384(JwtUtil.TOKEN_SECRET)).build().verify(token);
        Date date = decodedJwt.getExpiresAt();
        // 判断是否token有效
        if(date.getTime() > Calendar.getInstance().getTime().getTime()){
            System.out.println("token有效");
            userId = decodedJwt.getClaim("userId").asString();
            System.out.println("用户id:"+userId);
        } else {
            throw new TokenExpiredException("token失效");
        }
        return userId;
    }
}
