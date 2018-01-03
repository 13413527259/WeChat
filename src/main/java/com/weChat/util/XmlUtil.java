package com.weChat.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.weChat.bean.message.resp.Article;
import com.weChat.bean.message.resp.ImageMessage;
import com.weChat.bean.message.resp.MusicMessage;
import com.weChat.bean.message.resp.NewsMessage;
import com.weChat.bean.message.resp.TextMessage;
import com.weChat.bean.message.resp.VideoMessage;
import com.weChat.bean.message.resp.VoiceMessage;

/**
 * 消息与XML格式转换工具类
 * 
 * @author 13413527259
 *
 */
public class XmlUtil {

	/**
	 * 解析XML(请求)
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(request.getInputStream());
		Element root = doc.getRootElement();
		List<Element> nodes = root.elements();
		for (Element item : nodes) {
			map.put(item.getName(), item.getText());
		}
		return map;
	}

	/**
	 * 封装XML(响应)
	 */
	private static XStream xStream = new XStream();

	/**
	 * 文本消息对象转换成XML响应
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(TextMessage textMessage) {
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}

	/**
	 * 图片消息对象转换成XML响应
	 * 
	 * @param imageMessage
	 * @return
	 */
	public static String messageToXml(ImageMessage imageMessage) {
		xStream.alias("xml", imageMessage.getClass());
		return xStream.toXML(imageMessage);
	}

	/**
	 * 语音消息对象转换成XML响应
	 * 
	 * @param voiceMessage
	 * @return
	 */
	public static String messageToXml(VoiceMessage voiceMessage) {
		xStream.alias("xml", voiceMessage.getClass());
		return xStream.toXML(voiceMessage);
	}

	/**
	 * 视频消息对象转换成XML响应
	 * 
	 * @param videoMessage
	 * @return
	 */
	public static String messageToXml(VideoMessage videoMessage) {
		xStream.alias("xml", videoMessage.getClass());
		return xStream.toXML(videoMessage);
	}

	/**
	 * 音乐消息对象转换成XML响应
	 * 
	 * @param musicMessage
	 * @return
	 */
	public static String messageToXml(MusicMessage musicMessage) {
		xStream.alias("xml", musicMessage.getClass());
		return xStream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成XML响应
	 * 
	 * @param newsMessage
	 * @return
	 */
	public static String messageToXml(NewsMessage newsMessage) {
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", Article.class);
		return xStream.toXML(newsMessage);
	}

}
