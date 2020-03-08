package com.mabel.service;

import com.mabel.pojo.model.user.User;

import java.util.List;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2020-03-08 19:19
 **/
public interface AdminService {

    List<User> listAllUser();
}