package com.atguigu.service.impl;

import com.atguigu.mapper.UsersMapper;
import com.atguigu.pojo.Users;
import com.atguigu.service.UsersService;

public class UsersServiceImpl implements UsersService{
	private UsersMapper usersmapper;
	
	public UsersMapper getUsersmapper() {
		return usersmapper;
	}

	public void setUsersmapper(UsersMapper usersmapper) {
		this.usersmapper = usersmapper;
	}

	@Override
	public Users login(Users users) {
		return usersmapper.selByUsersPwd(users);
	}
}
