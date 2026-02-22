package com.ibm.controller;

import com.ibm.pojo.Emp;
import com.ibm.pojo.EmpQueryParam;
import com.ibm.pojo.PageBean;
import com.ibm.pojo.Result;
import com.ibm.service.EmpService;
import com.ibm.utils.GetProperties;
import jakarta.websocket.server.PathParam;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("emp")
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private ErrorPageRegistrar errorPageRegistrar;

    @Autowired
    private GetProperties getProperties;

    @GetMapping
    public Result getPageList(EmpQueryParam empQueryParam) {

        if(empQueryParam.getPageNo() < 1){
            empQueryParam.setPageNo(1);
        }

//    //    log.info("aliyunBucketName :  {}",bucketName);
//        log.info("=========Endpoint:{} =====  BucketName:{}========",getProperties.getEndpoint(),getProperties.getBucketName());

        log.info("===================   empQueryParam : {}",empQueryParam);

        //PageBean  pageBean =  empService.getPageList(empQueryParam.getPageNo(),empQueryParam.getPageSize());

        PageBean  pageBean =  empService.getPageList(empQueryParam);

        return Result.success(pageBean);
    }

    @PostMapping
    public Result addEmp(@RequestBody Emp emp) throws Exception {

        log.info("=================   addEmp : {}",emp);
        try{
            empService.addEmp(emp);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable Integer id) {
        log.info("员工Id====================="+id);

        try{
            Emp emp = empService.getEmpByID(id);
            return Result.success(emp);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping
    public Result updateEmp(@RequestBody  Emp emp) {

        try{
            log.info("=================   updateEmp  ===================");
            empService.updateEmp(emp);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }


    }
    @DeleteMapping("/ids")
    public Result deleteEmps( String ids) {

        log.info("=================   idsssss  ==================={}",ids);
        try {
            String id[] = ids.split(",");

            for (int i = 0; i < id.length; i++) {

                Integer delId =  Integer.parseInt(id[i]);
                empService.deleteById(delId);           }

            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }

    @DeleteMapping()
    public Result deleteEmp( Integer id) {

        log.info("=================   id  ==================={}",id);
        try {
            empService.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
