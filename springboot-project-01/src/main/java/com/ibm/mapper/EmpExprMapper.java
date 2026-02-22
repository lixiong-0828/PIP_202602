package com.ibm.mapper;

import com.ibm.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmpExprMapper {


    void addExpr(List<EmpExpr> empExprList);

    @Delete("delete from emp_expr where emp_id = #{empId} ")
    void deleteExpr(int empId);

    @Delete("delete from emp_expr where emp_id = #{id} ")
    void deleteById(Integer id);

}
