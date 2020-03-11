package com.mabel.service;

import com.mabel.pojo.dto.UserDTO;
import com.mabel.pojo.form.user.LoginForm;
import com.mabel.pojo.model.user.User;
import com.mabel.pojo.vo.ResponseEntity;

import java.util.List;

public interface UserService {

    UserDTO queryUserByUserName(String userName);

    Integer register(LoginForm loginForm);

    /**
     * loginSignature can be userName, name and phone
     * */
    ResponseEntity login(String loginSignature, String password);

    boolean updatePassword(LoginForm loginForm);

    List<User> listUser();

    User queryUserById(Integer userId);
}
