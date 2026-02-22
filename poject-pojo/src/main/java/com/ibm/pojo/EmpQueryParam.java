package com.ibm.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private String name;
    private Integer gender;

    private Integer pageNo =1 ;
    private Integer pageSize =5;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;

    private Integer dept_id;
    private Integer country_type;
    private Integer loginJob;
}
