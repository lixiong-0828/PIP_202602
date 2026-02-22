package com.ibm.pojo;

import lombok.Data;

@Data
public class Result {

    private Integer code;   //编码：1 成功 ，0 失败
    private String msg;     //错误信息
    private Object data;    //数据

    public static Result success(Object object) {
        Result result = new Result();
        result.code = 1;
        result.data = object;
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
