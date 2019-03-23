package com.mabel.service;

import com.mabel.pojo.model.User;

import java.util.List;

public interface UserService {

    User queryUserByNickname(String nickname);

    List<User> listUser();
}
