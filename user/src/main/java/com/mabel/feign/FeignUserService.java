package com.mabel.feign;

import com.mabel.pojo.model.user.User;
import com.mabel.service.UserService;
import com.mabel.user.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2020-03-08 19:07
 **/
@RestController
public class FeignUserService implements UserClient {

    @Autowired
    private UserService userService;

    @Override
    public List<User> listUser() {
        return userService.listUser();
    }
}