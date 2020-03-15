package mars.bookstore.base;
/**
 * 
 * <p>Title: BaseException.java</p>
 *
 * <p>Description: 基础异常类</p>
 *
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <p>Company: www.zycmars.cn</p>
 *
 * <p>JDK Version Used : JDK 8.0 +</p>
 *
 * @author zyc-mars
 *
 * @date 2019年4月13日
 *
 * @version 1.0.0
 */

public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorCode = "unknown.error";
	// 用户权限异常
	public static final String USER_AUTH_CODE = "50000";

	public BaseException() {
		super();
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public BaseException(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		// 否则用errorCode查询Properties文件获得出错信息
		return this.getErrorCode();
	}

	private String msg;

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setErrorCode(String code) {
		this.errorCode = code;
	}

	public String getMsg() {
		return msg;
	}

}
