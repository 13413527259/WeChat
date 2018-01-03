package com.weChat.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.weChat.bean.token.Oauth2Token;
import com.weChat.bean.user.SNSUserInfo;
import com.weChat.bean.user.UserAndLang;
import com.weChat.bean.user.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 请求用户信息-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月15日
 */
@Component
public class UserUtil {

	private static Logger logger = LoggerFactory.getLogger(UserUtil.class);

	private static String domain;

	private static String access_token;

	static {
		try {
			access_token = TokenUtil.getTokenInDB().getAccess_token();
			logger.info("access_token={}", access_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Value("${domain}")
	public void setDomain(String domain) {
		UserUtil.domain = domain;
	}

	public static String getDomain() {
		return domain;
	}

	// 获取用户信息-关注用户
	private static String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";

	// 批量获取用户基本信息
	private static String getUsersInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

	// 获取用户列表
	private static String getUserListUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

	// 授权登录
	private static String loginUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	// code换取token，此token非彼token~
	private static String codeToToKenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	// 获取用户信息-网页授权
	private static String SNSUerInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

	// 设置用户备注名
	private static String setRemarkUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";

	// 获取公众号的黑名单列表
	private static String getBlackListUrl = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=ACCESS_TOKEN";

	// 拉黑用户
	private static String addBlackUrl = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=ACCESS_TOKEN";

	// 取消拉黑用户
	private static String removeBlackUrl = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=ACCESS_TOKEN";

	/**
	 * 拉黑用户
	 * 
	 * @param begin_openid
	 * @throws Exception
	 */
	public static void addBlack(String[] openid_list) throws Exception {
		String url = addBlackUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("openid_list", openid_list);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
	}

	/**
	 * 取消拉黑用户
	 * 
	 * @param openid_list
	 * @throws Exception
	 */
	public static void removeBlack(String[] openid_list) throws Exception {
		String url = removeBlackUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("openid_list", openid_list);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
	}

	/**
	 * 获取公众号的黑名单列表
	 * 
	 * @param begin_openid
	 * @throws Exception
	 */
	public static void getBlackList(String begin_openid) throws Exception {
		begin_openid = begin_openid == null ? begin_openid = "" : begin_openid;
		String url = getBlackListUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("begin_openid", begin_openid);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
	}

	/**
	 * 设置用户备注名
	 * 
	 * @param openid
	 * @param remark
	 * @throws Exception
	 */
	public static void setRemark(String openid, String remark) throws Exception {
		String url = setRemarkUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("openid", openid);
		map.put("remark", remark);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
	}

	/**
	 * 获取用户列表
	 * 
	 * @param next_openid
	 * @throws Exception
	 */
	public static void getUserList(String next_openid) throws Exception {
		next_openid = next_openid == null ? next_openid = "" : next_openid;
		String url = getUserListUrl.replace("ACCESS_TOKEN", access_token).replaceAll("NEXT_OPENID", next_openid);
		logger.info("url={}", url);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.GET, null);
		logger.info("respBody={}", respBody);
	}

	/**
	 * 从用户关注公众号中获取用户信息(批量)
	 * 
	 * @param user_list
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<UserInfo> getUsersInfo(UserAndLang[] user_list) throws Exception {
		String url = getUsersInfoUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("user_list", user_list);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
		return JSONArray.toList(respBody.getJSONArray("user_info_list"), UserInfo.class);
	}

	/**
	 * 从用户关注公众号中获取用户信息
	 * 
	 * @param token
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public static UserInfo getUserInfo(String openid) throws Exception {

		String url = getUserInfoUrl.replace("ACCESS_TOKEN", access_token).replaceAll("OPENID", openid);

		logger.info("url={}", url);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.GET, null);
		logger.info("respBody={}", respBody);
		UserInfo userInfo = (UserInfo) JSONObject.toBean(respBody, UserInfo.class);
		return userInfo;

	}

	/**
	 * 授权登录页
	 * 
	 * @param state
	 * @param redirectUrl
	 * @throws Exception
	 */
	public static String getLoginUrl(String state, String redirectUrl) throws Exception {
		redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
		String url = loginUrl.replace("APPID", TokenInfo.APPID.getValue()).replace("REDIRECT_URI", redirectUrl)
				.replace("SCOPE", "snsapi_userinfo").replace("STATE", state);
		return url;

	}

	/**
	 * 授权登录页
	 * 
	 * @param state
	 * @param redirectUrl
	 * @throws Exception
	 */
	public static String getLoginUrl(String state) throws Exception {
		return getLoginUrl(state, domain + "/oauth2");
	}

	/**
	 * 确认授权后得到code，换取授权token
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static Oauth2Token getOauth2Token(String appid, String secret, String code) throws Exception {
		String url = codeToToKenUrl.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
		logger.info("url={}", url);
		JSONObject jsonObject = HttpsUtil.httpRequest(url, MethodType.GET, null);
		logger.info("respBody={}", jsonObject);
		Oauth2Token oauth2Token = (Oauth2Token) JSONObject.toBean(jsonObject, Oauth2Token.class);
		return oauth2Token;
	}

	/**
	 * 通过授权，获取用户信息
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static SNSUserInfo getUserInfo(Oauth2Token token) throws Exception {
		String url = SNSUerInfoUrl.replace("ACCESS_TOKEN", token.getAccess_token()).replace("OPENID",
				token.getOpenid());
		logger.info("url={}", url);
		JSONObject jsonObject = HttpsUtil.httpRequest(url, MethodType.GET, null);
		logger.info("respBody={}", jsonObject);
		SNSUserInfo userInfo = (SNSUserInfo) JSONObject.toBean(jsonObject, SNSUserInfo.class);
		return userInfo;
	}

	private static String getParmsJsonToMap(Map<?, ?> map) {
		return JSONObject.fromObject(map).toString();
	}
}
