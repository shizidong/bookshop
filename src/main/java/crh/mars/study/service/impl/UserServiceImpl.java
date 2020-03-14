package crh.mars.study.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.User;
import crh.mars.study.mapper.UserMapper;
import crh.mars.study.service.IUserService;


@Service("UserService")
@Transactional
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByUserId(String userId) throws BaseException {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public User getUserByUserName(String userName) throws BaseException {
		return userMapper.getUserByUserName(userName);
	}

	@Override
	public boolean updateUser(User user) throws BaseException {
		int result = userMapper.updateByPrimaryKey(user);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<User> queryAllUser() throws BaseException {
		return userMapper.selectAll();
	}

	@Override
	public boolean insertUser(User user) throws BaseException {
		int result = userMapper.insert(user);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

}