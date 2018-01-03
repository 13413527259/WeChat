package com.weChat.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weChat.bean.tag.Tag;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 管理用户标签-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月26日
 */
public class TagUtil {

	private static Logger logger = LoggerFactory.getLogger(TagUtil.class);

	private static String access_token;

	static {
		try {
			access_token = TokenUtil.getTokenInDB().getAccess_token();
			logger.info("access_token={}", access_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 创建标签
	private static String createTagUrl = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";

	// 获取公众号已创建的标签
	private static String getTagsUrl = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";

	// 编辑标签
	private static String putTagUrl = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";

	// 删除标签
	private static String delTagUrl = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";

	// 获取标签下粉丝列表
	private static String getUserToTagUrl = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";

	// 批量为用户打标签
	private static String setUsersToTagUrl = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";

	// 批量为用户取消标签
	private static String removeUsersToTagUrl = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";

	// 获取用户身上的标签列表
	private static String getTagstoUserUrl = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN";

	/**
	 * 创建标签
	 * 
	 * @param tagName
	 * @return tagId
	 * @throws Exception
	 */
	public static int createTag(String tagName) throws Exception {
		String url = createTagUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("tag", new Tag(tagName));
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
		return respBody.getJSONObject("tag").getInt("id");
	}

	/**
	 * 获取公众号已创建的标签
	 * 
	 * @return tag列表
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static List<?> getTags() throws Exception {
		String url = getTagsUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.GET, null);
		logger.info("respBody={}", respBody);
		return JSONArray.toList(JSONArray.fromObject(respBody.get("tag")), Tag.class);
	}

	/**
	 * 编辑标签
	 * 
	 * @param tag
	 * @throws Exception
	 */
	public static void putTag(Tag tag) throws Exception {
		String url = putTagUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("tag", tag);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
		if (respBody.getInt("errcode") != 0) {
			throw new Exception(respBody.toString());
		}
	}

	/**
	 * 删除标签
	 * 
	 * @param tagid
	 * @throws Exception
	 */
	public static void delTag(int tagid) throws Exception {
		String url = delTagUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("tag", new Tag(tagid));
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
		if (respBody.getInt("errcode") != 0) {
			throw new Exception(respBody.toString());
		}
	}

	/**
	 * 获取标签下粉丝列表,post请求,官方上是get
	 * 
	 * @param tagid
	 * @param next_openid
	 * @throws Exception
	 */
	public static void getUsersToTag(int tagid, String next_openid) throws Exception {
		String url = getUserToTagUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		// String parms = "&tagid=" + tagid + "&next_openid=" + next_openid;
		Map<String, Object> map = new HashMap<>();
		map.put("tagid", tagid);
		map.put("next_openid", next_openid);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
	}

	/**
	 * 批量为用户打标签
	 * 
	 * @param openid_list
	 * @param tagid
	 * @throws Exception
	 */
	public static void setUsersToTag(String[] openid_list, int tagid) throws Exception {
		String url = setUsersToTagUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("openid_list", openid_list);
		map.put("tagid", tagid);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
		if (respBody.getInt("errcode") != 0) {
			throw new Exception(respBody.toString());
		}
	}

	public static void removeUsersToTag(String[] openid_list, int tagid) throws Exception {
		String url = removeUsersToTagUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("openid_list", openid_list);
		map.put("tagid", tagid);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
		if (respBody.getInt("errcode") != 0) {
			throw new Exception(respBody.toString());
		}
	}

	public static void getTagsToUser(String openid) throws Exception {
		String url = getTagstoUserUrl.replace("ACCESS_TOKEN", access_token);
		logger.info("url={}", url);
		Map<String, Object> map = new HashMap<>();
		map.put("openid", openid);
		String parms = getParmsJsonToMap(map);
		logger.info("parms={}", parms);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		logger.info("respBody={}", respBody);
	}

	private static String getParmsJsonToMap(Map<?, ?> map) {
		return JSONObject.fromObject(map).toString();
	}
}
