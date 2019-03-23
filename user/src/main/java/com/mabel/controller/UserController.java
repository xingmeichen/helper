package com.mabel.controller;

import com.mabel.pojo.model.User;
import com.mabel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/v1/users")
    public ResponseEntity<User> queryUserByNickName() {
        User user = userServiceImpl.queryUserByNickname("Mabel Chen");
        System.out.println(user.getId());
        return null;
    }
}
