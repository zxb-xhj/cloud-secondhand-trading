package com.lyx.member.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author: xhj
 * @Date: 2023/11/15/20:17
 * @Description:
 */
public class JwtUtil {
    /**
     * 定义过期时间
     */
    private static final long EXPIRATION_TIME = 10000000L;

    /**
     * 生成token
     * @param subject
     * @return
     */
    public static String generateToken(String subject,String secretKey){ //secretKey 密钥
        return Jwts.builder()
                .setSubject(subject)  //表示JWT的主题，通常用于标识请求的发起者或用户
                .setIssuedAt(new Date())  //设置JWT的发行时间为当前时间
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)) //设置JWT的过期时间为当前时间加上预定义的EXPIRATION_TIME
                .signWith(SignatureAlgorithm.HS512,secretKey)  //使用HS512算法和传入的secretKey对JWT进行签名，以确保其安全性
                .compact();  //将JWT对象转换为紧凑的字符串表示形式
    }
    /**
     * 解析token,获取用户名
     * @param token
     * @return
     */
    public static Date getSubjectFromToken(String token,String secretKey){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)  //secretKey 密钥
                    .parseClaimsJws(token) //生成的token
                    .getBody();

            return claims.getIssuedAt();
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
//        System.out.println(JwtUtil.generateToken("xhj", "zhangxinbao"));
        Date fromToken = JwtUtil.getSubjectFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4aGoxMSIsImlhdCI6MTcwMDA1OTk2MywiZXhwIjoxNzAwMDU5OTY0fQ.QPkewkI9xqKcw-zXNt4tvypZ68CLkEiWKtDU6xHSvJwHGFOf8bJ9ceOmApHRe0ZSJPRUkmr1HnJvQAYbi6bZAA", "zhangxinbao");
//        Long o = fromToken.getTime();
        System.out.println(fromToken);
    }
}
