package com.mabel.controller;

import com.mabel.service.AdminService;
import com.mabel.pojo.vo.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2020-03-08 19:16
 **/
@RestController
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping("/v1/admins/users")
    public ResponseEntity listAllUser() {
        return ResponseEntity.success(adminService.listAllUser());
    }
}