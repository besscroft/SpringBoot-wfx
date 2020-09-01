package com.bess.springboot.wfx.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/1 17:57
 */
public class JWTUtil {
    /**
     * 解码token的方法
     * @param token
     * @return
     */
    public static Jws<Claims> Decrypt(String token) {
        return Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
    }

    /**
     * 加密token的方法
     * @param name
     * @param id
     * @return
     */
    public static String encrypt(String name,String id) {
        // 登录成功后生成token
        String token = Jwts.builder()
                .setSubject(name) // 设置名字
                .setId(id)    // 设置id
                .setIssuedAt(new Date()) // 设置token创建时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30 * 10)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, "fadj@Jq4$fka")  // 设置加密方式和密码
                .compact();
        return token;
    }

    public static String refreshEncrypt(String name,String id) {
        // 验证成功后生成新token
        String token = Jwts.builder()
                .setSubject(name) // 设置名字
                .setId(id)    // 设置id
                .setIssuedAt(new Date()) // 设置token创建时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30 * 10)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, "fadj@Jq4$fka")  // 设置加密方式和密码
                .compact();
        return token;
    }
}
