package com.mabel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mabel.constant.Constant;
import com.mabel.dao.UserDao;
import com.mabel.pojo.dto.user.UserDTO;
import com.mabel.pojo.dto.user.UserQueryDTO;
import com.mabel.pojo.form.user.LoginForm;
import com.mabel.pojo.model.HelperError;
import com.mabel.pojo.model.user.User;
import com.mabel.pojo.vo.ResponseEntity;
import com.mabel.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate redisTemplate;

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
        String encryptPassword = LoginForm.encryptPassword(loginForm.getPassword());
        user.setPassword(encryptPassword);
        Integer userId = userDao.addUser(user);
        if (null == userId || Integer.valueOf(0).equals(userId)) {
            return HelperError.SYSTEM_ERROR.getCode();
        }
        return userId;
    }

    @Override
    public ResponseEntity login(String loginSignature, String password) {
        User user = userDao.queryBySignature(loginSignature);
        if (null == user.getId()) {
            return ResponseEntity.fail(HelperError.SIGNATURE_PASSWORD_ERROR.getCode());
        }
        if (!LoginForm.checkPassword(password, user.getPassword())) {
            return ResponseEntity.fail(HelperError.SIGNATURE_PASSWORD_ERROR.getCode());
        }
        String token = LoginForm.generateToken(user.getId());
        // 设置登录有效期
        redisTemplate.opsForValue().set("login:" + String.valueOf(user.getId()), String.valueOf(user.getId()), Constant.ONE_DAY_SECONDS, TimeUnit.SECONDS);
        System.out.println("LoginKey is: " + redisTemplate.opsForValue().get("login:" + user.getId()));
        return ResponseEntity.success(token);
    }

    @Override
    public boolean updatePassword(LoginForm loginForm) {
        User user = userDao.queryByUserName(loginForm.getUserName());
        if (null == user.getId()) {
            return false;
        }
        String encryptPassword = LoginForm.encryptPassword(loginForm.getPassword());
        return userDao.updatePasswordById(user.getId(), encryptPassword);
    }

    @Override
    public List<User> listUser() {
        return userDao.listAllEffectiveUser();
    }

    @Override
    public User queryUserById(Integer userId) {
        return userDao.queryUserById(userId);
    }

    @Override
    public PageInfo<User> listUser(UserQueryDTO queryDTO) {
        return PageHelper.startPage(queryDTO.getPageNumber(), queryDTO.getPageSize())
                .doSelectPageInfo(() -> userDao.listAllEffectiveUser());
    }
}