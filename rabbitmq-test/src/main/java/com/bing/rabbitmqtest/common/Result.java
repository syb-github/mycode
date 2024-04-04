package com.bing.rabbitmqtest.common;

import lombok.Data;

/**
 * @author sunyibing
 * @date 2024/4/4
 */
@Data
public class Result {
    private int code;
    private String msg;
    private Object data;
    private boolean success;
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        result.setSuccess(true);
        return result;
    }
    public static Result fail(String msg) {
        Result result = new Result();
        result.setCode(-1);
        result.setMsg(msg);
        result.setData(null);
        result.setSuccess(false);
        return result;
    }
}
