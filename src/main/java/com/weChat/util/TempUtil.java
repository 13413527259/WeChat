package com.weChat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weChat.bean.temp.Temp;

import net.sf.json.JSONObject;

/**
 * 发送模板-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月21日
 */
public class TempUtil {

	private final static Logger logger = LoggerFactory.getLogger(TempUtil.class);

	// 设置行业
	private static String setIndustryUrl = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";

	// 获取设置的行业信息
	private static String getIndustryUrl = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";

	// 获得模板ID
	private static String getTempIdUrl = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";

	// 获取模板列表
	private static String getTempListUrl = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";

	// 删除模板
	private static String deleteTempUrl = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";

	// 发送模板消息
	private static String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	public static String tempToJsonStr(Temp temp) {
		return JSONObject.fromObject(temp).toString();
	}

	/**
	 * 发送模板消息
	 * 
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void send(String jsonStr) throws Exception {
		String url = sendUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		System.err.println(HttpsUtil.httpRequest(url, MethodType.POST, jsonStr));
	}

	/**
	 * 设置行业
	 * 
	 * @param id1
	 * @param id2
	 * @throws Exception
	 */
	public static void setIndustry(int id1, int id2) throws Exception {
		String url = setIndustryUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST,
				"{" + "\"industry_id1\":\"" + id1 + "\", \"industry_id2\":\"" + id2 + "\"}");
		logger.info("设置行业-respBody={}", respBody.toString());
	}

	/**
	 * 获取设置的行业信息
	 * 
	 * @throws Exception
	 */
	public static void getIndustry() throws Exception {
		String url = getIndustryUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.GET, null);
		logger.info("获取设置的行业信息-respBody={}", respBody.toString());
	}

	/**
	 * 获取模板列表
	 * 
	 * @throws Exception
	 */
	public static void getTempList() throws Exception {
		String url = getTempListUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.GET, null);
		logger.info("获取模板列表-respBody={}", respBody.toString());
	}

	/**
	 * 获取模板id
	 * 
	 * @throws Exception
	 */
	public static void getTempId(String template_id_short) throws Exception {
		String url = getTempIdUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST,
				"{" + "\"template_id_short\":\"" + template_id_short + "\"}");
		logger.info("获取模板id-respBody={}", respBody.toString());
	}

	/**
	 * 删除模板
	 * 
	 * @throws Exception
	 */
	public static void deleteTemp(String template_id) throws Exception {
		String url = deleteTempUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST,
				"{" + "\"template_id\":\"" + template_id + "\"}");
		logger.info("删除模板-respBody={}", respBody.toString());
	}

}
