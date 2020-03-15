package mars.bookstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mars.bookstore.base.BaseException;
import mars.bookstore.enty.User;
import mars.bookstore.mapper.UserMapper;
import mars.bookstore.service.IUserService;


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