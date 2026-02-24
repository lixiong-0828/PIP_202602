package com.ibm.mapper;

import com.ibm.pojo.Emp;
import com.ibm.pojo.EmpExpr;
import com.ibm.pojo.EmpQueryParam;
import com.ibm.pojo.LogInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select COUNT(*) from emp")
//    Integer getTotal();
//
//
//    @Select("select emp.* ,dept.name deptName from emp,dept where emp.dept_id = dept.id limit #{currentRow} , #{pageSize}")
//    List<Emp> getPageList(Integer currentRow, Integer pageSize);

    //导入【PageHelper】后写法。不用加【Liminit】语句，
        //@Select("select emp.* ,dept.name deptName from emp,dept where emp.dept_id = dept.id")
        List<Emp> getPageList(EmpQueryParam empQueryParam);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO emp (username, name, phone, job, salary, entry_date, image, create_time, update_time, dept_id)  " +
            "VALUES (#{username} ,#{name},#{phone} ,#{job} ,#{salary} ,#{entryDate} ,#{image} ,#{createTime} ,#{updateTime} ,#{deptId} )")
    void addEmp(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password};")
    Emp login(String username, String password);


    @Select("select emp.* , dept.country_type from emp left join dept on emp.dept_id = dept.id where emp.username = #{username} and emp.password = #{password};")
    Emp loginById(String username, String password);

    @Select("select * from emp where id = #{id} ")
    Emp getEmpById(Integer id);

    @Select("select * from emp_expr where emp_id = ${id}")
    List<EmpExpr> getEmpExprById(Integer id);

    @Update("update emp set username = #{username}  , name=#{name} ,gender = #{gender} ,phone = #{phone} ,job = #{job} ,salary=#{salary} ,dept_id = #{deptId} ,entry_date=#{entryDate} ,update_time=#{updateTime} , image=#{image} where id=#{id} ")
    void updateEmp(Emp emp);

    @Delete("delete from emp where id = #{id} ")
    void deleteById(Integer id);

}
