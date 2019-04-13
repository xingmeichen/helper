package com.mabel.service.impl;

import com.mabel.dao.UserDao;
import com.mabel.pojo.dto.UserDTO;
import com.mabel.pojo.form.user.LoginForm;
import com.mabel.pojo.model.HelperError;
import com.mabel.pojo.model.user.User;
import com.mabel.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserDTO queryUserByUserName(String userName) {
        User user = userDao.queryByUserName(userName);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setUserId(user.getId());
        return userDTO;
    }

    @Override
    public Integer register(LoginForm loginForm) {
        User existUser = userDao.queryByUserName(loginForm.getUserName());
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
    public String login(String loginSignature, String password) {
        User user = userDao.queryBySignature(loginSignature);
        if (null == user.getId()) {
            return HelperError.SIGNATURE_PASSWORD_ERROE.getName();
        }
        if (!LoginForm.checkPassword(password, user.getPassword())) {
            return HelperError.SIGNATURE_PASSWORD_ERROE.getName();
        }
        return LoginForm.generateToken(user.getId());
    }
}