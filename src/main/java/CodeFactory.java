import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * 订单编码码生成器，生成32位数字编码，
 *
 * @author jiwengjian
 * @生成规则 1位单号类型+17位时间戳+14位(用户id加密&随机数)
 * Date:2017年9月8日上午10:05:19
 */
public class CodeFactory {
	/**
	 * 随即编码
	 */
	private static final int[] r = new int[]{7, 9, 6, 2, 8, 1, 3, 0, 5, 4};
	/**
	 * 用户id和随机数总长度
	 */
	private static final int maxLength = 14;


	/**
	 * 更具id进行加密+加随机数组成固定长度编码
	 */
	private static String toCode(Long id) {
		String idStr = id.toString();
		StringBuilder idsbs = new StringBuilder();
		for (int i = idStr.length() - 1; i >= 0; i--) {
			idsbs.append(r[idStr.charAt(i) - '0']);
		}
		return idsbs.append(getRandom(maxLength - idStr.length())).toString();
	}


	/**
	 * 生成秒级时间字符串
	 */
	public static String getDateTime() {
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	/**
	 * 生成固定长度随机码
	 *
	 * @param n 长度
	 */
	private static long getRandom(long n) {
		long min = 1, max = 9;
		for (int i = 1; i < n; i++) {
			min *= 10;
			max *= 10;
		}
		return (((long) (new Random().nextDouble() * (max - min)))) + min;
	}

	/**
	 * 生成不带类别标头的编码
	 *
	 */
	private static synchronized String getCode() {
		return getDateTime() + toCode(1L);
	}


	/**
	 * 生成通用编号
	 *
	 * @param userId
	 */
	static String getNumber() {
		return getCode();
	}

}