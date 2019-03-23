package com.mabel.service.impl;

import com.mabel.mapper.UserMapper;
import com.mabel.pojo.model.User;
import com.mabel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

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

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByNickname(String nickname) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("nickname", nickname);
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return new User();
        }
        return users.get(0);
    }

    @Override
    public List<User> listUser() {
        return new ArrayList<>();
    }
}