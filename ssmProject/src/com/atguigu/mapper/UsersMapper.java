package com.atguigu.mapper;

import org.apache.ibatis.annotations.Select;

import com.atguigu.pojo.Users;

public interface UsersMapper {
	@Select("select * from user_info where username = #{username} and password = #{password}")
	Users selByUsersPwd(Users users);
}
