package com.mabel.service;

import com.mabel.pojo.dto.UserDTO;
import com.mabel.pojo.form.user.LoginForm;
import com.mabel.pojo.vo.ResponseEntity;

public interface UserService {

    UserDTO queryUserByUserName(String userName);

    Integer register(LoginForm loginForm);

    /**
     * loginSignature can be userName, name and phone
     * */
    ResponseEntity login(String loginSignature, String password);

    boolean updatePassword(LoginForm loginForm);
}
