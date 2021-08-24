package com.mabel.mapper;

import com.mabel.pojo.model.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserMapper extends Mapper<User> {

    User queryUserById(@Param("userId") Integer userId);
}
