package com.ibm.controller;

import com.ibm.pojo.Result;
import com.ibm.pojo.dept;
import com.ibm.service.DeptService;
import com.ibm.service.imp.DeptServiceImp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {


    /**
     * 一个接口有还几个是现实，DI时要以下方法来指定
     * ①primary
     * ②Autowired + Qualifier("")
     * ③Resource(name="")
      */
//    @Qualifier("deptServiceImp2")  //指定要生效的【Bean】的名字
//    @Autowired
    @Resource (name = "deptServiceImp")  //*一个接口有好几个实现时用。【Resource】注解，就不需要【Autowired】和【Qualifier】注解
    private DeptService deptService; // = new DeptServiceImp();

    /**
     *改造后的3层架构：【Controller】
     * @return
     */
    //@RequestMapping(value = "/depts", method = RequestMethod.GET)  //限定只能【GET】请求
    //@GetMapping("/depts")
    @GetMapping
    public Result list(Integer countryType) {

        List<dept> deptList = deptService.getDept(countryType);
        //相应数据
        return Result.success(deptList);
    }

    /**
     * 方式二
     * 当传递来的参数明和，接受的参数名不一致时，用【RequestParam】。 【id】 --> 【deptID】
     * 但是使用【RequestParam】，参数必须传过来。
     * 不想传时，@RequestParam(value = "id",required = false),追加【required = false】
     * 因为默认是【required = ture】
     * @param deptId
     * @return
     */
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id",required = false) Integer deptId) {
//        System.out.println(deptId);
//
//        return Result.success();
//    }

    /**
     * 方式三
     * 当传来的参数名和接受的参数名一样时，省略【RequestParam】
     * @param id
     * @return
     */
    //@DeleteMapping("/depts")
    @DeleteMapping
    public Result delete(Integer id) {
        System.out.println(id);
       deptService.deleteById(id);
        return Result.success();
    }


//    /**
//     * 方式一
//     * 当传来的参数名和接受的参数名一样时，省略【RequestParam】
//     * @param id
//     * @return
//     */
////    // 以前的写法，参数的接受方法。
//    @DeleteMapping
//    public Result delete(HttpServletRequest request) {
//        String id = request.getParameter("id");
//        Integer deptId = Integer.parseInt(id);
//
//        System.out.println(deptId);
//
//        return Result.success();
//    }
    @PostMapping
    //@PostMapping("/depts")
    public Result add(@RequestBody dept dept) {

        log.info(dept.toString());
        try{
            deptService.add(dept);
            return Result.success();
        } catch (Exception e) {
            log.info("***********************"+e.getMessage());
            return Result.error(e.getMessage());
        }


    }

    /**
     * 注意：
     * 【@GetMapping("/depts")】<--- 这个注解不能重复出现。改成，后面家【/{id}】
     * 而且，配套加【@PathVariable】注解
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    //@GetMapping("/depts/{id}")
    public Result getDeptById(@PathVariable Integer id ) {
        log.info("Id====================="+id);
        dept dept= deptService.getDeptByIs(id);
        log.info("Id====================="+dept);
        return Result.success(dept);
    }


    @PutMapping
    //@PutMapping("/depts")
    public Result update(@RequestBody dept dept) {
        log.info(dept.toString());
        deptService.updateName(dept);
        return Result.success();

    }
}
