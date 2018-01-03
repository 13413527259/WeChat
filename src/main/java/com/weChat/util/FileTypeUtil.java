package com.weChat.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 判断文件类型-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月24日
 */
public class FileTypeUtil {

	private static Map<String, String> fileTypes = new HashMap<>();

	static {
		// image/thumb
		fileTypes.put("ffd8ffe000104a464946", "image"); // JPEG (jpg)
		fileTypes.put("89504e470d0a1a0a0000", "image"); // PNG (png)
		fileTypes.put("47494638396126026f01", "image"); // GIF (gif)
		// video
		// fileTypes.put("2e524d46000000120001", "rmvb"); // rmvb
		// fileTypes.put("464c5601050000000900", "flv"); // flv
		fileTypes.put("00000020667479706d70", "video"); // mp4
		// fileTypes.put("000001ba210001000180", "mpg"); // mpg
		// fileTypes.put("52494646d07d60074156", "avi"); // avi
		fileTypes.put("52494646e27807005741", "video"); // Wave (wav)
		// voice
		// fileTypes.put("3026b2758e66cf11a6d9", "wmv"); // wmv与asf相同
		// fileTypes.put("4d546864000000060001", "mid");//mid
		fileTypes.put("49443303000000002176", "voice");// mp3
		fileTypes.put("2E7261FD", "voice"); // Real Audio (ram)
	}

	/**
	 * 根据头文件字节判断文件类型，而不是根据文件后缀 http://blog.csdn.net/yudajun/article/details/50263499
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String getFileType(String filePath) throws Exception {
		String type = null;
		File file = new File(filePath);
		if (!file.exists() || file.length() < 6) {
			throw new Exception("文件不存在");
		}
		FileInputStream fileIn = new FileInputStream(file);
		byte[] b = new byte[5];
		fileIn.read(b, 0, b.length);
		String head = bytesToHexString(b);
		for (String key : fileTypes.keySet()) {
			if (key.contains(head)) {
				type = fileTypes.get(key);
				break;
			}
		}
		fileIn.close();
		return type;
	}

	/**
	 * Java中byte与16进制字符串的互相转换
	 * http://blog.csdn.net/androiddeveloper_lee/article/details/6619414
	 * 
	 * @param src
	 * @return
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
