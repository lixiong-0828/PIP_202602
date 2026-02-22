package com.ibm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInfo {

    private Integer id;
    private String name;
    private String username;
    private String token;
    private Integer job;
    private Integer dept_id;
    private Integer countryType;
}
