package com.mabel.service.impl;

import com.mabel.pojo.model.User;
import com.mabel.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-03-23 11:23
 **/
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User queryUserByNickname(String nickname) {
        return new User();
    }

    @Override
    public List<User> listUser() {
        return new ArrayList<>();
    }
}