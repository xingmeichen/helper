package com.mabel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabel.pojo.dto.user.UserDTO;
import com.mabel.pojo.form.user.LoginForm;
import com.mabel.pojo.vo.ResponseEntity;
import com.mabel.service.PersonService;
import com.mabel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("teacher")
    private PersonService teacherService;

    @Autowired
    @Qualifier("student")
    private PersonService student;

    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/v1/users")
    public ResponseEntity queryUserByNickName(@RequestParam String nickname) {
        LOGGER.info(nickname);
        UserDTO userDTO = userServiceImpl.queryUserByUserName(nickname);
        ResponseEntity responseEntity = ResponseEntity.success();
        responseEntity.setData(userDTO);
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
        responseEntity.setData(result);
        return responseEntity;
    }

    @PostMapping("/v1/users/login")
    public ResponseEntity login(@RequestParam String loginSignature, @RequestParam String password) {
        return userServiceImpl.login(loginSignature, password);
    }

    @PutMapping("/v1/users/password")
    public ResponseEntity updatePassword(@RequestBody LoginForm loginForm) {
        boolean result = userServiceImpl.updatePassword(loginForm);
        if (result) {
            return ResponseEntity.success();
        }
        return ResponseEntity.fail();
    }

    @PutMapping("v1/users/logout")
    public ResponseEntity logout(@RequestParam String loginSignature) {
        // TODO 
        return null;
    }

    @GetMapping("v1/users/test")
    public ResponseEntity test(@RequestParam String loginSignature) {
        LOGGER.info("to test: ");
        return ResponseEntity.success();
    }

    @GetMapping("v1/users/test-more-than-one-implement")
    public ResponseEntity testMoreThanOneImplement() {
        String teacherTask = teacherService.task();
        LOGGER.info("[teacher's task is {}]", teacherTask);
        String studentTask = student.task();
        LOGGER.info("[student's task is {}]", studentTask);
        ObjectMapper mapper;
        return ResponseEntity.success();
    }
}
