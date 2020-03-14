package crh.mars.study.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 
 * <p>Title: ArrayUtil.java</p>
 *
 * <p>Description:数组相关的工具类 </p>
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

public final class ArrayUtil extends ArrayUtils {
	
	protected static final String[] EMPTY_STRING_ARRAY = new String[0];

	public static String[] toStringArray(String value, String delim) {
		if (value != null) {
			return StringUtil.split(delim, value);
		}
		return EMPTY_STRING_ARRAY;
	}

	public static Object[] removeElement(Object[] arr, Object target) {
		List<Object> list = new ArrayList<Object>(arr.length);
		for (int i = 0; i < arr.length; ++i) {
			if ((target == arr[i])
					|| ((target != null) && (target.equals(arr[i])))) {
				continue;
			}
			list.add(arr[i]);
		}

		return list.toArray();
	}

	/**
	 * @description 把数组转化为以分隔符连接的字符串
	 * @param array 数组 delim 分隔符
	 * @return 以分隔符连接的字符串
	 */
	public static String arrayToStr(Object[] array, String delim) {
	    StringBuilder result = new StringBuilder();
	    if (isEmpty(array)) {
	        return result.toString();
	    }
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]).append(delim);
        }
        return result.deleteCharAt(result.length()-1).toString();
	}
	/**
	 * @describe 将数组转化成List
	 * @param
	 * @return
	 */
	public static List<Object> arrayToList(Object array) {
		if (array == null) {
			return new ArrayList<Object>();
		}
		if (array instanceof Object[]) {
			return Arrays.asList((Object[]) array);
		}
		int size = Array.getLength(array);
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < size; ++i) {
			list.add(Array.get(array, i));
		}
		return list;
	}

	public static boolean isEmptyStringArray(String[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		}
		String[] arrayOfString = array;
		int j = array.length;
		for (int i = 0; i < j; ++i) {
			String item = arrayOfString[i];
			if (!StringUtil.isEmpty(item)) {
				return false;
			}
		}
		return true;
	}

	public static Object[] removeElements(Object[] arr, Object target) {
		List<Object> list = new ArrayList<Object>(arr.length);
		for (int i = 0; i < arr.length; ++i) {
			if ((target == arr[i])
					|| ((target != null) && (target.equals(arr[i])))) {
				continue;
			}
			list.add(arr[i]);
		}
		return list.toArray();
	}
}
