package whut.util;

public class StringUtil {
	/**
	 * 判断一个字符串是否为空
	 * @param str 待判断的字符串
	 * @return 为空返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 去除字符串前面的空格
	 * @param str 待处理的字符串
	 * @return 去除前缀中的空格的字符串
	 */
	public static String removePreSpace(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() != 0 && sb.charAt(0) == ' ') {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		String str = "   asd  sd";
		System.out.println(str);
		System.out.println(StringUtil.removePreSpace(str));
	}
}
