package com.ibm.service.imp;

import com.ibm.dao.DeptDao;
import com.ibm.dao.imp.DeptDaoImp;
import com.ibm.mapper.DeptMapper;
import com.ibm.pojo.dept;
import com.ibm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 【Service】层
 */
//@Component
@Service //把生成的实力交给IOC容器管理
public class DeptServiceImp implements DeptService {

    @Autowired //注入依赖，程序运行时，从IOC容器获得
    private DeptDao deptDao;  // = new DeptDaoImp(); =》 使用了注入依赖，不需要new了！

//    @Override
//    public List<dept> getDept() {
//        System.out.println("DeptServiceImp11111111111111111111");
//        List<String> lines = deptDao.getDept();
//
//        List<dept> deptList = lines.stream().map(line -> {
//            String[] split = line.split(",");
//            int id = Integer.parseInt(split[0]);
//            String name = split[1];
//            LocalDateTime updateTime = LocalDateTime.parse(split[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return new dept(id, name, updateTime);
//        }).collect(Collectors.toList());
//
//        return deptList;
//    }

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<dept> getDept(Integer countryType) {

        return deptMapper.findAll(countryType);

    }

    @Override
    public void deleteById(Integer id) {
       //System.out.println("deleted count:" + deptMapper.deleteById(id));
        deptMapper.deleteById(id);
    }

    @Override
    public void add(dept dept) {

        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        try {

            deptMapper.add(dept);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public dept getDeptByIs(Integer id )  {
        System.out.println("getDeptByIs ===================================");
        return deptMapper.getDeptById(id);
    }

    @Override
    public void updateName(dept dept) {

        dept.setUpdateTime(LocalDateTime.now());
        System.out.println("before go mapper :" + dept);
        deptMapper.updateName(dept);
    }


}
