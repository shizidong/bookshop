package crh.mars.study.utils;

import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;


/**
 * 
 * <p>Title: StringUtil.java</p>
 *
 * <p>Description:字符串相关处理工具类 </p>
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
public class StringUtil extends StringUtils {
	
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String[] split(String seperators, String list, boolean include) {
		StringTokenizer tokens = new StringTokenizer(list, seperators, include);
		String[] result = new String[tokens.countTokens()];
		int i = 0;

		while (tokens.hasMoreTokens())
			result[(i++)] = tokens.nextToken();
		return result;
	}

	public static String[] split(String src, String flag) {
		return StringUtils.splitPreserveAllTokens(src, flag);
	}
}
