package com.ibm.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class GetJwtUtil {

    /**
     * 令牌生成
     */
    public static String getJwt(Map<String,Object> claims) {

//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", 999);
//        claims.put("name", "GuanYu");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "5p2O6ZuE")  //【"5p2O6ZuE"】：base64 加密/解密 时的密码
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*10))  //10分钟期限的令牌
                .addClaims(claims)
                .compact();

        //System.out.println(jwt);
        return jwt;
    }

    /**
     * 解析令牌
     * 令牌解析出错，2种情况：
     * 1，令牌被串改
     * 2，令牌的失效：有效期已过
     * @throws IOException
     */
    public static Claims paarseJwt(String jwt) throws IOException {

        Claims claims = Jwts.parser()
                .setSigningKey("5p2O6ZuE")    //这个必须跟，加密时的密钥一致！！
                .parseClaimsJws(jwt) //是【parseClaimsJws】，不是【parseClaimsJwt】
                .getBody();

        //System.out.println(claims);
        return claims;


    }


}
