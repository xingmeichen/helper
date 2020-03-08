package com.mabel.service.impl;

import com.mabel.service.AdminService;
import com.mabel.pojo.model.user.User;
import com.mabel.user.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2020-03-08 19:19
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserClient userClient;

    @Override
    public List<User> listAllUser() {
        return userClient.listUser();
    }
}