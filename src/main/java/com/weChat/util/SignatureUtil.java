package com.weChat.util;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;



/**
 * 检验signature工具类
 * @author 13413527259
 *
 */
public class SignatureUtil {
	
	private static String token="MyWeChatApp";
	
	/**
	 * 校验签名
	 * @param signature 微信加密签名，signature结合了开发者填写的token和请求中的timestamp、nonce<br>
	 * @param timestamp 时间戳<br>
	 * @param nonce 随机数<br>
	 * @return
	 */
	public static boolean isSignature(String signature,String timestamp,String nonce) {
		//1.字典排序
		String[] arr=new String[] {token,timestamp,nonce}; 
		Arrays.sort(arr);
		
		//2.sha1加密
		StringBuilder content=new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		String checkout=DigestUtils.sha1Hex(content.toString());
		
		//3.加密后的字符串对比signature
		return checkout!=null?checkout.equals(signature):false;
	}

}
