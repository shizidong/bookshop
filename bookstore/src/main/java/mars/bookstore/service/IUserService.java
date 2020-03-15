package mars.bookstore.service;

import java.util.List;
import mars.bookstore.base.BaseException;
import mars.bookstore.enty.User;

public interface  IUserService {

	public User getUserByUserId(String userId) throws BaseException;

	public User getUserByUserName(String userName)throws BaseException;

	public boolean updateUser(User user)throws BaseException;

	public List<User> queryAllUser()throws BaseException;

	public boolean insertUser(User user)throws BaseException;

}
