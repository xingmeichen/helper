package com.mabel.pojo.vo;

import com.mabel.pojo.model.HelperError;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-03-31 10:11
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ResponseEntity {

    private String message;
    private Integer errorCode;
    private Boolean success;
    private Object data;

    public static ResponseEntity success() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setMessage("success").setErrorCode(0).setSuccess(true);
        return responseEntity;
    }

    public static ResponseEntity fail() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setMessage("系统错误").setErrorCode(-1).setSuccess(false);
        return responseEntity;
    }

    public static ResponseEntity fail(Integer errorCode) {
        ResponseEntity responseEntity = new ResponseEntity();
        HelperError helperError = HelperError.getEnumByCode(errorCode);
        responseEntity.setErrorCode(helperError.getCode())
                .setMessage(helperError.getName());
        return responseEntity;
    }
}