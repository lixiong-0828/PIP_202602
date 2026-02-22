package com.ibm.http;//package com.lixiong.pojo.http;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 【CorsConfig】是，前后端跨域访问时，限制不明的访问！！！
 * 只有被允许的【路径】才能访问后端。
 * 如：以下【"http://localhost:4200"】
 *
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){

        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost:5173"); //允许【Angular】的前端访问
        config.addAllowedOrigin("http://localhost:88"); //允许【VUE】的前端访问
        config.addAllowedOrigin("http://localhost:80"); //允许【VUE】的前端访问
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
