package com.mabel.controller;

import com.mabel.pojo.form.user.LoginForm;
import com.mabel.pojo.model.user.User;
import com.mabel.pojo.vo.ResponseEntity;
import com.mabel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/v1/users")
    public ResponseEntity queryUserByNickName(@RequestParam String nickname) {
        User user = userServiceImpl.queryUserByUserName(nickname);
        ResponseEntity responseEntity = ResponseEntity.success();
        responseEntity.setData(user);
        return responseEntity;
    }

    @PostMapping("/v1/users/register")
    public ResponseEntity register(@RequestBody LoginForm loginForm) {
        ResponseEntity responseEntity = ResponseEntity.success();
        Integer result = userServiceImpl.register(loginForm);
        if (null == result || result.compareTo(0) < 0) {
            responseEntity = ResponseEntity.fail(result);
            return responseEntity;
        }
        return responseEntity;
    }
}
