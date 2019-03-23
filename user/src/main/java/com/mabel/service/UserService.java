package com.mabel.service;

import com.mabel.pojo.model.User;

public interface UserService {

    User queryUserByNickname(String nickname);
}
