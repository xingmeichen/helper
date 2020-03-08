package com.mabel.user;

import com.mabel.pojo.model.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2020-03-08 18:53
 **/
@FeignClient("user")
public interface UserClient {

    @GetMapping("/feign/v1/users")
    List<User> listUser();
}