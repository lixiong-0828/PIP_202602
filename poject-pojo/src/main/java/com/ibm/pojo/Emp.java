package com.ibm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private Integer gender;
    private String phone;
    private Integer job;
    private Integer salary;
    private LocalDate entryDate;
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deptId;

    private String deptName;
    private Integer countryType;

    private List<EmpExpr> empExprList;


}
