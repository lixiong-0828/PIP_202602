package com.ibm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan  //开启对Java原生组件的支持：比如 【Filter】
public class SpringbootProject01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProject01Application.class, args);
    }

}
