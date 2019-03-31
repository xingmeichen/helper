package com.mabel.controller;

import com.alibaba.fastjson.JSONObject;
import com.mabel.pojo.model.User;
import com.mabel.pojo.vo.ResponseEntity;
import com.mabel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/v1/users")
    public ResponseEntity queryUserByNickName(@RequestParam String nickname) {
        User user = userServiceImpl.queryUserByNickname(nickname);
        ResponseEntity responseEntity = ResponseEntity.success();
        responseEntity.setData(user);
        String jsonString = JSONObject.toJSONString(user);
        System.out.println(jsonString);
        return responseEntity;
    }
}
