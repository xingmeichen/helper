package com.mabel.pojo.model;

import lombok.Getter;

import java.util.Arrays;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-04-05 17:06
 **/
@Getter
public enum  HelperError {

    SYSTEM_ERROR(-1, "系统错误"),
    DUPLICATE_USER_NAME_ERROR(-2, "用户名重复"),
    SIGNATURE_PASSWORD_ERROE(-3, "用户名或密码错误");

    private Integer code;
    private String name;

    HelperError(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static HelperError getEnumByCode(Integer code) {
        return Arrays.asList(HelperError.values()).stream()
                .filter(item -> null != code && code.equals(item.getCode())).findFirst().orElse(SYSTEM_ERROR);
    }

    public static String getNameByCode(Integer code) {
        HelperError helperError = Arrays.asList(HelperError.values()).stream()
                .filter(item -> null != code && code.equals(item.getCode())).findFirst().orElse(SYSTEM_ERROR);
        return helperError.getName();
    }
}