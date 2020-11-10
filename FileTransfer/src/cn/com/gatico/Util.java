package cn.com.gatico;

public class Util {

	/**
	 * int到byte[]
	 *
	 * @param i
	 * @return
	 */
	public static byte[] intToByte(int i) {
		byte[] result = new byte[4];
		// 由高位到低位
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * byte[]转int
	 *
	 * @param bytes
	 * @return
	 */
	public static int byteToInt(byte[] bytes) {
		int value = 0;
		// 由高位到低位
		for (int i = 0; i < 4; i++) {
			value <<= 8;
			value |= (bytes[i] & 0xff);// 往高位游
		}
		return value;
	}

	/**
	 * 字节数组转成long 8位
	 *
	 * @param buffer
	 * @return
	 */
	public static long byteToLong(byte[] buffer) {
		long values = 0;
		for (int i = 0; i < 8; i++) {
			values <<= 8;
			values |= (buffer[i] & 0xff);
		}
		return values;
	}


	/**
	 * 数字转字节数组
	 *
	 * @param num
	 * @return
	 */
	public static byte[] longToByte(long num) {
		byte[] arr = new byte[8];
		for (int j = 0; j < arr.length; j++) {
			arr[j] = (byte) ((num >> (8 - j - 1) * 8) & 0xFF);
		}
		return arr;
	}
}
