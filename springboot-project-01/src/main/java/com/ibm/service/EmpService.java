package com.ibm.service;

import com.ibm.pojo.Emp;
import com.ibm.pojo.EmpQueryParam;
import com.ibm.pojo.LogInfo;
import com.ibm.pojo.PageBean;

public interface EmpService {
    PageBean getPageList(EmpQueryParam empQueryParam);

    void addEmp(Emp emp) throws Exception;

    LogInfo login(Emp emp);

    LogInfo loginById(String id, String password);

    Emp getEmpByID(Integer id);

    void updateEmp(Emp emp);

    void deleteById(Integer id);

//    void deleteByIds(String ids);
}
