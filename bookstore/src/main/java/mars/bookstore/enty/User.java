package mars.bookstore.enty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDY_USER")
public class User {
	// 用户Id
	@Id
	private String userId;
	// 用户姓名
	private String userName;
	// 用户密码
	private String userPassword;
	// 用户令牌
	private String userToken;
	// 用户编码
	private String userCode;
	// 用户真实姓名
	private String userRealname;
	// 用户状态
	private Integer userStatus;
	// 用户是否为管理员
	private Integer userIsadmin;
	// 用户电话
	private String userPhone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getUserIsadmin() {
		return userIsadmin;
	}

	public void setUserIsadmin(Integer userIsadmin) {
		this.userIsadmin = userIsadmin;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}
