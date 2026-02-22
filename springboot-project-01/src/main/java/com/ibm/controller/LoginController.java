package com.ibm.controller;

import com.ibm.pojo.Emp;
import com.ibm.pojo.LogInfo;
import com.ibm.pojo.Result;
import com.ibm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @GetMapping("/login")
    public Result login(@RequestBody Emp emp) {

        log.info("emp={}", emp);
        log.info(" Test log for push code to git hub!! Lab: #20280826-002");

        LogInfo logInfo = empService.login(emp);

        if (logInfo != null) {
            return Result.success(logInfo);
        }else {
            return Result.error("error Login");
        }


        //return Result.success();
    }

    @GetMapping("/loginById")
    public Result loginById( String username, String password) {

        LogInfo logInfo = empService.loginById(username,password);

        if (logInfo != null) {
            return Result.success(logInfo);
        }else {
            return Result.error("error Login");
        }


        //return Result.success();
    }
}
