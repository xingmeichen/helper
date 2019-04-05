package com.mabel.dao;

import com.mabel.mapper.UserMapper;
import com.mabel.pojo.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-04-05 16:44
 **/
@Service
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public User queryByUserName(String userName) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName", userName);
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return new User();
        }
        return users.get(0);
    }

    public Integer addUser(User user) {
        if (null == user) {
            return 0;
        }
        userMapper.insertSelective(user);
        return user.getId();
    }
}