package com.ibm.service;

import com.ibm.pojo.Emp;
import com.ibm.pojo.EmpLog;
import com.ibm.pojo.EmpQueryParam;
import com.ibm.pojo.PageBean;

public interface EmpLogService {

    void addEmpLog(EmpLog empLog) throws Exception;
}
