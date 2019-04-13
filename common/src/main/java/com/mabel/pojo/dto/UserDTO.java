package com.mabel.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-04-13 10:46
 **/
@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {

    private Integer userId;
    private String userName;
    private String name;
    private String email;
    private String phone;
    private String idCode;
}