package com.ibm.dao.imp;

import com.ibm.dao.DeptDao;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

//@Component  //把生成的实力交给IOC容器管理
@Repository //把生成的实力交给IOC容器管理
public class DeptDaoImp implements DeptDao {
    /**
     * 【Dao】层
     * @return
     */
    @Override
    public List<String> getDept() {

        //加载并读写 【dept.txt】文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dept.txt");
        List<String> lines = IOUtils.readLines(inputStream, "UTF-8");

        return lines;
    }
}
