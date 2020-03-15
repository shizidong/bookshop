package mars.bookstore.contorller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mars.bookstore.base.BaseException;
import mars.bookstore.enty.User;
import mars.bookstore.service.IUserService;
import mars.bookstore.utils.LogUtil;
import mars.bookstore.utils.WebResult;

@RequestMapping("/study/user")
@RestController
public class UserContorller {

	@Autowired
	private IUserService userService;

	// 用户登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public WebResult<String> userLogin(String userName,String userPassword) {
		WebResult<String> webResult = new WebResult<>();
		String token = "false";
		try {
			User tempUser = userService.getUserByUserName(userName);
			if(tempUser != null) {
				if (tempUser.getUserName().equals(userName)) {
					if (tempUser.getUserPassword().equals(userPassword)) {
						token = RandomStringUtils.randomAlphanumeric(32);
						if(tempUser.getUserIsadmin()==1) {
							token=token+"1";
						}else {
							token=token+"0";
						}
						tempUser.setUserToken(token);
						//userService.updateUser(user);
					}else {
						return webResult.success("用户名或密码错误！！！", token);
					}
				}else {
					return webResult.success("用户名或密码错误！！！", token);
				}
			}else {
				return webResult.success("用户不存在！！！", token);
			}
		} catch (BaseException e) {
			return webResult.fail(e);
		}
		return webResult.success("用户登录成功", token);
	}
	
	// 获取所有用户
		@RequestMapping(value = "/query/allUser", method = RequestMethod.POST)
		public @ResponseBody List<User> queryAllUser() {
			List<User> userList = new ArrayList<>();
			try {
				userList = userService.queryAllUser();
			} catch (BaseException e) {
				LogUtil.info("查询所有用户失败！");
			}
			return userList;
		}

	// 修改用户
	@RequestMapping(value = "/update/user", method = RequestMethod.POST)
	public WebResult<String> updateUser(User user) {
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = userService.updateUser(user);
		} catch (BaseException e) {
			LogUtil.info("更新用户失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("更新用户成功！", String.valueOf(result));
	}
	// 新增用户
	@RequestMapping(value = "/insert/user", method = RequestMethod.POST)
	public WebResult<String> insertUser(User user) {
		WebResult<String> webResult = new WebResult<>();
		String token="false";
		user.setUserId(token);
		user.setUserCode("0");
		token = RandomStringUtils.randomAlphanumeric(32);
		boolean result = true;
		try {
			result = userService.insertUser(user);
		} catch (BaseException e) {
			LogUtil.info("新增用户失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("新增用户成功！", String.valueOf(result));
	}

}
