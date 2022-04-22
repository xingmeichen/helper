package com.mabel.dao;

import com.mabel.mapper.UserMapper;
import com.mabel.pojo.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @project: helper
 * @description:
 * 这一层是只对数据库进行操作，不做任何的业务处理
 * 增加这一层旨在规范数据库操作，一旦有发生数据表设计的改动不需要全局查询有哪些类需要做更改，
 * 而只需要关注对应的DAO层就够了
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

    @Transactional
    public Integer addUser(User user) {
        if (null == user) {
            return 0;
        }
        userMapper.insertSelective(user);
        return user.getId();
    }

    public User queryBySignature(String signature) {
        Example example = new Example(User.class);
        example.createCriteria().orEqualTo("userName", signature)
                .orEqualTo("name", signature)
                .orEqualTo("phone", signature);
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return new User();
        }
        return users.get(0);
    }

    public boolean updatePasswordById(Integer userId, String encryptPassword) {
        User user = new User();
        user.setId(userId).setPassword(encryptPassword);
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    public List<User> listAllEffectiveUser() {
        List<User> users = userMapper.selectAll();
        return users.stream().filter(item -> 0 == item.getDisabled()).collect(Collectors.toList());
    }

    public User queryUserById(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        /**
         * 如果查询到了已经删除的用户，则返回null
         * */
        if (null != user && !Objects.equals(0, user.getDisabled())) {
            return null;
        }
        return user;
    }
}