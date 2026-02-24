package com.ibm.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ibm.mapper.EmpExprMapper;
import com.ibm.mapper.EmpLogMapper;
import com.ibm.mapper.EmpMapper;
import com.ibm.pojo.*;
import com.ibm.service.EmpLogService;
import com.ibm.service.EmpService;
import com.ibm.utils.GetJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImp implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogMapper empLogMapper;
    @Autowired
    private EmpService empService;
    @Autowired
    private EmpLogService empLogService;

    @Autowired
    private GetJwtUtil getJwtUtil;


//    ============== 传统的做法 ===============
//    @Override
//    public PageBean getPageList(Integer pageNo, Integer pageSize) {
//
//        log.info("getPageList: pageNo=" + pageNo + ", pageSize=" + pageSize );
//        log.info("=============PageNo={}, ==========PageSize={}",pageNo, pageSize );
//        //get total list number
//        Integer total =   empMapper.getTotal();
//
//        Integer currentRow = (pageNo-1)  * pageSize;
//
//        log.info("===================currentRow: {})" , currentRow);
//        List<Emp> empList = empMapper.getPageList(currentRow, pageSize);
//
//
//        return new PageBean(total,empList);
//    }

    //导入【PageHelper】后的写法
    @Override
    public PageBean getPageList(EmpQueryParam empQueryParam) {

        log.info("getPageList: pageNo= {}" , empQueryParam.getName() );

//        log.info("getPageList: pageNo=" + pageNo + ", pageSize=" + pageSize );
//        log.info("=============PageNo={}, ==========PageSize={}",pageNo, pageSize );

        PageHelper.startPage(empQueryParam.getPageNo(), empQueryParam.getPageSize());

        List<Emp> empList = empMapper.getPageList(empQueryParam);

        Page<Emp> page = (Page<Emp>)empList;

        return new PageBean(page.getTotal(), page.getResult());
    }

    //@Transactional  //SpringBoot事物管理注解 （注意：只能抛出【RuntimeException】时才有效！！）
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addEmp(Emp emp) throws Exception {

        try {
            // add to table Emp
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.addEmp(emp);

            //int i = 5/0;  //用来测试事物管理的代码<<-- 这个异常属于【RuntimeException】
            //用来测试【@Transactional(rollbackFor = Exception.class)】
//        if (true) {
//            throw new Exception("测试用代码。看看【@Transactional】事物能不能有效");
//        }

            // add to table Emp_Expr
            List<EmpExpr> empExprList = emp.getEmpExprList();
            if(!CollectionUtils.isEmpty(empExprList)){
                empExprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });

                empExprMapper.addExpr(empExprList);
            }


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            EmpLog empLog = new EmpLog(null,emp.toString(),LocalDateTime.now());
             empLogService.addEmpLog(empLog);

        }
    }

    @Override
    public LogInfo login(Emp parmEmp) {

        Emp emp = empMapper.login(parmEmp.getUsername(),parmEmp.getPassword());

        if (emp != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", emp.getId());
            claims.put("name", emp.getName());
            claims.put("username", emp.getUsername());

            String jwt = getJwtUtil.getJwt(claims);

            log.info("============jwt:{}=============",jwt);

            return new LogInfo(emp.getId(),emp.getName(),emp.getUsername(),jwt ,emp.getJob() ,emp.getDeptId() ,emp.getCountryType());
        }else{
            return null;
        }


        //return new LogInfo();
    }

    @Override
    public LogInfo loginById(String username, String password) {

        Emp emp = empMapper.loginById(username, password);

        if (emp != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", emp.getId());
            claims.put("name", emp.getName());
            claims.put("username", emp.getUsername());

            String jwt = getJwtUtil.getJwt(claims);

            log.info("============jwt:{}=============", jwt);

            return new LogInfo(emp.getId(), emp.getName(), emp.getUsername(), jwt ,emp.getJob() ,emp.getDeptId() ,emp.getCountryType());
        } else {
            return null;
        }
    }

    @Override
    public Emp getEmpByID(Integer id) {

        try{

            Emp emp = empMapper.getEmpById(id);

            List<EmpExpr> empExprList = empMapper.getEmpExprById(id);

            emp.setEmpExprList(empExprList);

            return emp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateEmp(Emp emp) {

        try{
            log.info("=================   updateEmp service : {}",emp);
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.updateEmp(emp);
            empExprMapper.deleteExpr(emp.getId());

            // add to table Emp_Expr
            List<EmpExpr> empExprList = emp.getEmpExprList();
            if(!CollectionUtils.isEmpty(empExprList)){
                empExprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });

                empExprMapper.addExpr(empExprList);
            }


        } catch (Exception e) {
            log.error("=================   updateEmp error : {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {

        try{
            empMapper.deleteById(id);
            empExprMapper.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void deleteByIds(String ids) {
//        try{
//
//            log.info("=================in service ----  idsssss  ==================={}",ids);
//            empMapper.deleteByIds(ids);
//            empExprMapper.deleteByIds(ids);
//        } catch (Exception e) {
//            log.info("=================   error : {}",e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
}
