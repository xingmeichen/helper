package com.mabel.service;

import com.mabel.pojo.form.user.LoginForm;
import com.mabel.pojo.model.user.User;

import java.util.List;

public interface UserService {

    User queryUserByUserName(String userName);

    Integer register(LoginForm loginForm);

    List<User> listUser();
}
