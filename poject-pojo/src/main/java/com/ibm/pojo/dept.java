package com.ibm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor  //无参构造函数
@AllArgsConstructor //全参构造函数
public class dept {
    private Integer id;// recommend use object not int
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer countryType;
}
