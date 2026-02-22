//package com.ibm;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//@Slf4j
//public class TestGenJWT {
//
//    /**
//     * 令牌生成
//     */
//    @Test
//
//    public void test() {
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", 999);
//        claims.put("name", "GuanYu");
//
//        String jwt = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256, "5p2O6ZuE")  //【"5p2O6ZuE"】：base64 加密/解密 时的密码
//                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*10))  //10分钟期限的令牌
//                .addClaims(claims)
//                .compact();
//
//        log.info("jwt:{}", jwt);
//        //System.out.println(jwt);
//    }
//
//    /**
//     * 解析令牌
//     * 令牌解析出错，2种情况：
//     * 1，令牌被串改
//     * 2，令牌的失效：有效期已过
//     * @throws IOException
//     */
//    @Test
//    public void testParseJwt() {
//
//        try {
//            Claims claims = Jwts.parser()
//                    .setSigningKey("5p2O6ZuE")    //这个必须跟，加密时的密钥一致！！
//                    .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3NTM0MTQ4ODQsIm5hbWUiOiLmm7nmk40iLCJpZCI6NCwidXNlcm5hbWUiOiJDYW9DYW8ifQ.PStgRVXnJhEBu4d_pFwNH4zuJ4NDC0rlaOOcqP5TqyM")
//                    .getBody();
//
//            System.out.println(claims);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
//
//    }
//
//
//}
