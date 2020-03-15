package mars.bookstore.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import mars.bookstore.base.BaseException;

/**
 * 
 * <p>Title: WebResult.java</p>
 *
 * <p>Description: 前端返回获取工具类</p>
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
public class WebResult<T extends Object> {
	
	private String status ;
	private String msg ;
	private T data;
	private String exception ;
	
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public interface Code {

        /** 成功 */
        int OK = 200;

        /** 失败 */
        int FAIL = 201;

        /** 已存在 */
        int EXIST = 202;

        /** 不存在 */
        int NOT_EXIST = 203;

        /** 请求错误 */
        int BAD_REQUEST = 400;

        /** 需要认证 */
        int UNAUTHORIZED = 401;

        /** 请求被拒绝 */
        int FORBIDDEN = 403;

        /** 找不到页面 */
        int NOT_FOUND = 404;

        /** 请求超时 */
        int REQUEST_TIMEOUT = 408;

        /** 服务器内部错误 */
        int INTERNAL_SERVER_ERROR = 500;
    }
    
    /**
     * Description: JSON返回成功
     *
     * @return 成功的JSON map
     */
    public WebResult<T> success(String message,T obj){
    	 this.setData(obj);
    	 this.setStatus(WebResult.Code.OK+"");
    	 this.setMsg(message);
         return this;
    }
    
    
    /**
     * Description: JSON返回失败
     *
     * @return
     */
    public WebResult<T> fail(String message){
    	 this.setData(null);
    	 this.setMsg(message);
    	 this.setStatus(WebResult.Code.FAIL+"");
    	 LogUtil.error(message);
         return this;
    }

    
    /**
     * Description: JSON返回失败
     *
     * @return
     */
    public WebResult<T> fail(BaseException baseException){
    	 this.setData(null);
    	 final Writer result = new StringWriter();
         final PrintWriter printWriter = new PrintWriter(result);
         baseException.printStackTrace(printWriter);
    	 this.setMsg(baseException.getMessage());
    	 this.setStatus(baseException.getErrorCode());
    	 //记录日志
    	 this.setException(result.toString());
    	 //LogUtil.error(result.toString(), baseException);
    	 return this;
    }

    /**
     * Description: JSON返回失败
     *
     * @return
     */
    public WebResult<T> fail(String message,Exception e){
    	 this.setData(null);
    	 this.setMsg(message);
    	 this.setStatus(WebResult.Code.FAIL+"");
    	 final Writer result = new StringWriter();
         final PrintWriter printWriter = new PrintWriter(result);
         e.printStackTrace(printWriter);
         LogUtil.error(message,e);
    	 this.setException(result.toString());
         return this;
    }
    

}
