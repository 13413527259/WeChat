package com.weChat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weChat.bean.meterial.Article;
import com.weChat.bean.meterial.News;
import com.weChat.bean.meterial.NewsPut;

import net.sf.json.JSONObject;

/**
 * 媒体素材-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月24日
 */
public class MediaUtil {

	private static Logger logger = LoggerFactory.getLogger(MediaUtil.class);

	// 上传临时素材
	private static String uploadInterimUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	// 获取临时素材
	private static String getInterimUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	// 新增永久图文素材
	private static String uploadNewsUrl = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";

	// 上传图文消息内的图片获取URL
	private static String uploadNewsImgUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

	// 新增其他类型永久素材
	private static String uploadForeverUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

	// 获取永久素材
	private static String getForeverUrl = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";

	// 删除永久素材
	private static String delForeverUrl = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";

	// 修改永久图文素材
	private static String updateNewsUrl = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";

	// 获取素材总数
	private static String getCountUrl = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";

	// 获取素材列表
	private static String getListUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";

	/**
	 * 上传临时素材
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String uploadInterim(String filePath) throws Exception {
		String url = uploadInterimUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		String type = FileTypeUtil.getFileType(filePath);
		url = url.replace("TYPE", type);
		logger.info("filePath={},type={}", filePath, type);
		JSONObject respBody = HttpsUtil.uploadHttpRequest(url, filePath);
		logger.info("respBody={}", respBody);
		return respBody.getString("media_id");
	}

	/**
	 * 下载临时素材(图片)
	 * 
	 * @param media_id
	 * @return
	 * @throws Exception
	 */
	public static String getInterim(String media_id) throws Exception {
		// request.getSession().getServletContext().getRealPath("").replaceAll("\\\\",
		// "/")+"/img/";
		String url = getInterimUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token())
				.replace("MEDIA_ID", media_id);
		String respBody = HttpsUtil.downHttpRequest(url, System.getProperty("user.dir") + "\\down\\");
		logger.info("respBody={}", respBody);
		return respBody;
	}

	public static String uploadNewsImg(String filePath) throws Exception {
		String url = uploadNewsImgUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.uploadHttpRequest(url, filePath);
		logger.info("respBody={}", respBody);
		return respBody.toString();
	}

	/**
	 * 构建newsPut参数
	 * 
	 * @param title
	 * @param thumb_media_id
	 * @param author
	 * @param digest
	 * @param show_cover_pic
	 * @param content
	 * @param content_source_url
	 * @param media_id
	 * @param index
	 * @return
	 */
	public static String NewsBuildToString(String title, String thumb_media_id, String author, String digest,
			int show_cover_pic, String content, String content_source_url, String media_id, Integer index) {
		Article article = new Article(title, thumb_media_id, author, digest, show_cover_pic, content,
				content_source_url);
		List<Article> articles = new ArrayList<>();
		articles.add(article);
		NewsPut put = new NewsPut();
		put.setArticles(articles);
		put.setMedia_id(media_id);
		put.setIndex(index);
		return JSONObject.fromObject(put).toString();
	}

	/**
	 * 构建news参数
	 * 
	 * @param title
	 * @param thumb_media_id
	 * @param author
	 * @param digest
	 * @param show_cover_pic
	 * @param content
	 * @param content_source_url
	 * @return
	 */
	public static String NewsBuildToString(String title, String thumb_media_id, String author, String digest,
			int show_cover_pic, String content, String content_source_url) {
		Article article = new Article(title, thumb_media_id, author, digest, show_cover_pic, content,
				content_source_url);
		List<Article> articles = new ArrayList<>();
		articles.add(article);
		News news = new News();
		news.setArticles(articles);
		return JSONObject.fromObject(news).toString();
	}

	/**
	 * 上传永久图文
	 * 
	 * @param news
	 * @return
	 * @throws Exception
	 */
	public static String uploadNews(String news) throws Exception {
		String url = uploadNewsUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, news);
		logger.info("respBody={}", respBody);
		return respBody.toString();
	}

	/**
	 * 上传其他类型永久素材(视频文件除外)
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String uploadForever(String filePath) throws Exception {
		String url = uploadForeverUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		String type = FileTypeUtil.getFileType(filePath);
		url = url.replace("TYPE", type);
		JSONObject respBody = HttpsUtil.uploadHttpRequest(url, filePath);
		logger.info("respBody={}", respBody);
		return respBody.getString("media_id");
	}

	/**
	 * 获取永久素材(返回结果需要按类型分类)
	 * 
	 * @param media_id
	 * @return
	 * @throws Exception
	 */
	public static String getForever(String media_id) throws Exception {
		String url = getForeverUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		Map<String, Object> map = new HashMap<>();
		map.put("media_id", media_id);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, getParmsTojson(map));
		logger.info("respBody={}", respBody);
		return respBody.toString();
	}

	/**
	 * 删除永久素材
	 * 
	 * @param media_id
	 * @return
	 * @throws Exception
	 */
	public static String delForever(String media_id) throws Exception {
		String url = delForeverUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token())
				.replace("MEDIA_ID", media_id);
		Map<String, Object> map = new HashMap<>();
		map.put("media_id", media_id);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, getParmsTojson(map));
		logger.info("respBody={}", respBody);
		return respBody.toString();
	}

	public static String updateNews(String newsPut) throws Exception {
		String url = updateNewsUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, newsPut);
		logger.info("respBody={}", respBody);
		return respBody.toString();
	}

	/**
	 * 获取永久素材总数
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCount() throws Exception {
		String url = getCountUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.GET, null);
		logger.info("respBody={}", respBody);
		return respBody.toString();
	}

	/**
	 * 获取某个类型素材的列表
	 * 
	 * @param type
	 * @param offset
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public static String getList(String type, int offset, int count) throws Exception {
		String url = getListUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("offset", offset);
		map.put("count", count);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, getParmsTojson(map));
		logger.info("respBody={}", respBody);
		return respBody.toString();
	}

	private static String getParmsTojson(Map<String, Object> map) {
		return JSONObject.fromObject(map).toString();
	}

}
