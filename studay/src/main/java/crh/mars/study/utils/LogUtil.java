
package crh.mars.study.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogUtil {

    private static Map<String, Logger> loggerMap = new HashMap<>();

    private static final String LEVEL_DEBUG = "0";
    private static final String LEVEL_ERROR = "1";
    private static final String LEVEL_INFO = "2";
    private static final String LEVEL_WARN = "3";
    private static final String SYS_PRE = "sys_";
    

    private LogUtil() {
    }


    public static void debug(Object message) {
        String className = new String("");
        String method = new String("");
        StackTraceElement classNameTemp = getClassName();
        if(null!=classNameTemp){
            className = classNameTemp.getClassName();
            method = classNameTemp.getMethodName();
        }
        String threadName = Thread.currentThread().getName();
        saveLog(LEVEL_DEBUG, threadName, className, method, message);
        Logger log = getLogger(className);
        if (null!=log && log.isDebugEnabled()) {
            log.debug(message.toString());
        }
    }

    public static void info(Object message) {
        String className = new String("");
        String method = new String("");
        StackTraceElement classNameTemp = getClassName();
        if(null!=classNameTemp){
            className = classNameTemp.getClassName();
            method = classNameTemp.getMethodName();
        }
        String threadName = Thread.currentThread().getName();
        saveLog(LEVEL_INFO, threadName, className, method, message);
        Logger log = getLogger(className);
        if (null!=log && log.isInfoEnabled()) {
            log.info(message.toString());
        }
    }

    public static void warn(Object message) {
        String className = new String("");
        String method = new String("");
        StackTraceElement classNameTemp = getClassName();
        if(null != classNameTemp){
            className = classNameTemp.getClassName();
            method = classNameTemp.getMethodName();
        }
        String threadName = Thread.currentThread().getName();
        saveLog(LEVEL_WARN, threadName, className, method, message);
        Logger log = getLogger(className);
        if (null != log) {
            log.warn(message.toString());
        }
    }
    /**
     * 
     * Description: 记录错误日志通用工具
     *
     * @param message 自定义信息
     * @param t 异常
     */
    public static void error(Object message) {
    	//线程名、类名、方法名
    	String threadName = Thread.currentThread().getName();
        String className = new String("");
        String method = new String("");
        StackTraceElement classNameTemp = getClassName();
        if(null!=classNameTemp){
            className = classNameTemp.getClassName();
            method = classNameTemp.getMethodName();
        }
        
        Logger log = getLogger(className);
        //如果传入的是异常则打印堆栈信息
        if (message instanceof Throwable) {
            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);
            log.error(printWriter.toString());
//            ((Throwable)message).printStackTrace(printWriter);
        	message = result.toString();
        }
        //保存日志到本地
        
        if (null != log) {
            log.error(message.toString());
        }
    }

    /**
     * 
     * Description: 记录错误日志通用工具
     *
     * @param message 自定义信息
     * @param t 异常
     */
    public static void error(Object message, Throwable t) {
    	//线程名、类名、方法名
    	String threadName = Thread.currentThread().getName();
        String className = new String("");
        String method = new String("");
        StackTraceElement classNameTemp = getClassName();
        if(null!=classNameTemp){
            className = classNameTemp.getClassName();
            method = classNameTemp.getMethodName();
        }
        Logger log = getLogger(className);
        // 获取堆栈信息
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
//        t.printStackTrace(printWriter);
        log.error(printWriter.toString());
       
        //保存日志到本地
        if (null != log) {
            log.error(message.toString(), t);
        }
    }

    /**
     * 获取最开始的调用者所在类
     * 
     * @return
     */
    private static StackTraceElement getClassName() {
        Throwable th = new Throwable();
        StackTraceElement[] stes = th.getStackTrace();
        if (stes == null) {
            return null;
        }
        StackTraceElement ste = stes[2];
        return ste;
    }

    /**
     * 根据类名获得logger对象
     * 
     * @param className
     * @return
     */
    private static Logger getLogger(String className) {
        Logger log = null;
        if (loggerMap.containsKey(className)) {
            log = loggerMap.get(className);
        } else {
            try {
                log = LoggerFactory.getLogger(Class.forName(className));
                loggerMap.put(className, log);
            } catch (ClassNotFoundException e) {
            	LogUtil.error("日志记录反射类异常",e);
            }
        }
        return log;
    }

    /**
     * http远程rest调用
     * 
     * @param interfaceUrl
     * @param method
     * @param msg
     */
    @SuppressWarnings("unused")
	private static void saveLog(String level, String threadName, String interfaceUrl, String method, Object msg) {
		String nodeName = null;
    	//默认各种标识
		HttpServletRequest request = null;
    	String url = "local";
    	String userId = SYS_PRE+nodeName;
    	String token = userId;
       try {  
        	// 组装http远程调用
            Map<String, Object> params = new HashMap<>();
            params.put("userId", userId);
            params.put("ipAddr", url);
            params.put("token", token);
            params.put("opTime",DateUtil.date2String(new Date()));
            params.put("sysId", nodeName);
            params.put("nodeName", nodeName);
            params.put("levelId", level);
            params.put("threadName", threadName);
            params.put("interfaceUrl", interfaceUrl + "/" + method);
            params.put("errorMsg",URLEncoder.encode(msg.toString()));//特殊符号处理
            HttpUtil.sendPost("/api/log/monitor/save", params);
        } catch (Exception e) {
            StackTraceElement ste = getClassName();
  	      	if (null != ste) {
                String className = ste.getClassName();
                Logger log = getLogger(className);
                if (null != log) {
                    log.error("给JAUTH同步日志出错", e);
                }
            }
        }
    }
    
    /**
     * 单纯的打印出来日志，不给JAUTH记录
     */
    public static void outInfo(Object message) {
        String className = new String("");
        StackTraceElement classNameTemp = getClassName();
        if(null!=classNameTemp){
            className = classNameTemp.getClassName();
        }
        Logger log = getLogger(className);
        if (null!=log && log.isInfoEnabled()) {
            log.info(message.toString());
        }
    }
}
