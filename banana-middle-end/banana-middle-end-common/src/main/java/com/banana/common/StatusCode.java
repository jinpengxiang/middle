package com.banana.common;

import com.banana.starter.entity.IStatusCode;

public enum StatusCode implements IStatusCode {
    //成功
    SUCCESS("0000"),
    //Feign消费失败
    FEIGN_FAILED("9998"),
        //缺少请求体
    MISSING_REQUEST_BODY("4001"),
    //缺少请求参数
    MISSING_PARAM("4002"),
    //参数不合法
    WRONG_PARAM("4003"),
    //资源不存在
    RESOURCE_NOT_EXIST("4004"),
    //资源已存在
    RESOURCE_EXIST("4005"),
    //错误
    ERROR("9999")
    ;
    private String code;

    StatusCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
