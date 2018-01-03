package com.weChat.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weChat.bean.qecode.QRCodBuild;
import com.weChat.bean.qecode.Ticket;

import net.sf.json.JSONObject;

/**
 * 二维码-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月21日
 */
@SuppressWarnings("rawtypes")
public class QRCodeUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(QRCodeUtil.class);
	
	//长连接变短连接
	private static String longToshortUrl="https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
	
	// 创建临时二维码
	private static String createShortQRCodeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKENPOST";

	// 创建永久二维码
	@SuppressWarnings("unused")
	private static String createLongQRCodeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKENPOST";

	// 用ticket换取二维码，注意TICKET记得进行UrlEncode
	private static String ticketToQRCodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

	/**
	 * 创建二维码
	 * 
	 * @param build
	 *            需要的参数封装在QRCodBuild
	 * @return
	 * @throws Exception
	 */
	public static Ticket createQRCode(QRCodBuild build) throws Exception {
		String url = createShortQRCodeUrl.replace("TOKENPOST", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, JSONObject.fromObject(build).toString());
//		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}");
		Ticket ticket = (Ticket) JSONObject.toBean(respBody, Ticket.class);
		LOGGER.info("ticick={}", ticket.toString());
		return ticket;
	}

	public static Ticket createQRCode(int scene_id) throws Exception {
		Map<String, Map> map = new HashMap<>();
		Map<String, Object> scene_parms = new HashMap<>();
		scene_parms.put("scene_id", scene_id);
		map.put("scene", scene_parms);
		QRCodBuild build = new QRCodBuild(600, "QR_SCENE", map);
		JSONObject buildStr = JSONObject.fromObject(build);
		LOGGER.info("build={}", buildStr);
		return createQRCode(build);
	}
	
	public static Ticket createQRCode(String scene_str) throws Exception {
		Map<String, Map> map = new HashMap<>();
		Map<String, Object> scene_parms = new HashMap<>();
		scene_parms.put("scene_id", scene_str);
		map.put("scene", scene_parms);
		QRCodBuild build = new QRCodBuild(6000, "QR_STR_SCENE", map);
		JSONObject buildStr = JSONObject.fromObject(build);
		LOGGER.info("build={}", buildStr);
		return createQRCode(build);
	}

	public static String getQRCodeUrl(String ticket) throws Exception {
		ticket = URLEncoder.encode(ticket, "utf-8");
		String url = ticketToQRCodeUrl.replace("TICKET", ticket);
//		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.GET, null);
		LOGGER.info("url={}", url);
		return url;
	}
	
	public static String longToShort(String longUrl) throws Exception {
		String url = longToshortUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		Map<String, String> parms=new HashMap<>();
		parms.put("action", "long2short");
		parms.put("long_url", longUrl);
		JSONObject respBody=HttpsUtil.httpRequest(url, MethodType.POST, JSONObject.fromObject(parms).toString());
		LOGGER.info("respBody={}",respBody);
		String short_url=respBody.getString("short_url");
		LOGGER.info("long_url={}",longUrl);
		LOGGER.info("short_url={}",short_url);
		return short_url;
	}

}
