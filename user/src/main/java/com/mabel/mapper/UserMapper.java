package com.mabel.mapper;

import com.mabel.pojo.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}
