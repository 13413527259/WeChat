package com.weChat.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

import net.sf.json.JSONObject;

/**
 * JSSKD-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月16日
 */
public class JSSKDUtil {

	private static String getjsapi_ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	/**
	 * 获取JSSDK
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getJSSDK_ticket() throws Exception {
		String url = getjsapi_ticketUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject jsonObject = HttpsUtil.httpRequest(url, MethodType.GET, null);
		System.err.println("jssdk:" + jsonObject);
		return jsonObject == null ? null : jsonObject.getString("ticket");
	}

	/**
	 * 产生签名
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getSignatureParms(String url) throws Exception {
		// url=URLEncoder.encode(url,"utf-8");
		Map<String, String> map = new HashMap<String, String>();
		// 这里的jsapi_ticket是获取的jsapi_ticket。
		String jsapi_ticket = getJSSDK_ticket();
		String nonce_str = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String str;
		String signature = "";

		// 参数拼接注意这里参数名必须全部小写，且必须有序
		str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;

		// sha1加密
		signature = DigestUtils.sha1Hex(str);

		map.put("url", url);
		map.put("jsapi_ticket", jsapi_ticket);
		map.put("nonceStr", nonce_str);
		map.put("timestamp", timestamp);
		map.put("signature", signature);

		map.put("appid", TokenInfo.APPID.getValue());

		return map;
	}

}
