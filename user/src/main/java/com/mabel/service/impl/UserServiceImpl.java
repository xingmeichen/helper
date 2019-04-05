package com.mabel.service.impl;

import com.mabel.dao.UserDao;
import com.mabel.pojo.form.user.LoginForm;
import com.mabel.pojo.model.HelperError;
import com.mabel.pojo.model.user.User;
import com.mabel.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserByUserName(String userName) {
        return userDao.queryByUserName(userName);
    }

    @Override
    public Integer register(LoginForm loginForm) {
        User existUser = this.queryUserByUserName(loginForm.getUserName());
        if (StringUtils.isNotBlank(existUser.getUserName())) {
            return HelperError.DUPLICATE_USER_NAME_ERROR.getCode();
        }
        User user = new User();
        BeanUtils.copyProperties(loginForm, user);
        Integer userId = userDao.addUser(user);
        if (null == userId || Integer.valueOf(0).equals(userId)) {
            return HelperError.SYSTEM_ERROR.getCode();
        }
        return userId;
    }

    @Override
    public List<User> listUser() {
        return new ArrayList<>();
    }
}