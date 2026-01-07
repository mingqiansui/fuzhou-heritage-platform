package com.heritage.auth.mapper;
import com.heritage.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username=#{username}")
    User findByUsername(String username);
}
