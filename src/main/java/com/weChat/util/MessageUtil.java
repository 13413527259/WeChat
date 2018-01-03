package com.weChat.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.weChat.bean.message.resp.Article;
import com.weChat.bean.message.resp.NewsMessage;
import com.weChat.bean.message.resp.TextMessage;
import com.weChat.bean.temp.Item;
import com.weChat.bean.temp.Temp;
import com.weChat.bean.temp.TemplateItem;

import net.sf.json.JSONObject;

/**
 * 消息处理-业务
 * 
 * @author 13413527259
 *
 */
public class MessageUtil {

	/**
	 * 处理请求
	 * 
	 * @param request
	 * @return
	 */
	public static String parseRequest(HttpServletRequest request) {
		// XML格式的消息数据(用于响应)
		String respXml = null;
		// 默认响应的文本消息内容
		String respContent = "未知的消息类型！";

		try {
			Map<String, String> map = XmlUtil.parseXml(request);

			Set<String> key = map.keySet();

			// 发送方帐号
			String fromUserName = map.get("FromUserName");
			// 开发者微信号
			String toUserName = map.get("ToUserName");
			// 消息类型
			String msgType = map.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageType.REQ_MESSAGE_TYPE_TEXT.getValue());

			// 文本消息
			if (msgType.equals(MessageType.REQ_MESSAGE_TYPE_TEXT.getValue())) {
				respContent = "您发送的是文本消息！";
				if (map.get("Content").contains("捐赠")) {
					Temp temp = new Temp();
					temp.setTouser(fromUserName);
					temp.setUrl("http://baidu.com");
					temp.setTemplate_id("2hbq7NlPOWcbVBDlaTOMXtsPX9HP76HYvk0cWuLe5iY");
					temp.setTopcolor("#FF0000");
					TemplateItem templateItem = new TemplateItem();
					templateItem.put("first", new Item("捐赠爱心礼物成功！", "#0000FF"));
					templateItem.put("orderMoneySum", new Item("50", "#0000FF"));
					templateItem.put("orderProductName", new Item("一次描述", "#0000FF"));
					templateItem.put("Remark", new Item(
							"\n我们已收到您的捐款，开始为您打爱心物资，山区小朋友很快就能收到亲的爱心! \n\n如有问题请致电400-828-1878或直接在微信留言，小易将第一时间为您服务！",
							"#FC00E8"));
					temp.setData(templateItem);
					TempUtil.send(TempUtil.tempToJsonStr(temp));
				}
			}
			// 图片消息
			else if (msgType.equals(MessageType.REQ_MESSAGE_TYPE_IMAGE.getValue())) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageType.REQ_MESSAGE_TYPE_VOICE.getValue())) {
				respContent = "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageType.REQ_MESSAGE_TYPE_VIDEO.getValue())) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageType.REQ_MESSAGE_TYPE_LOCATION.getValue())) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageType.REQ_MESSAGE_TYPE_LINK.getValue())) {
				respContent = "您发送的是链接消息！";
			}
			// 事件推送
			else if (msgType.equals(EventType.REQ_MESSAGE_TYPE_EVENT.getValue())) {
				// 事件类型
				String event = map.get("Event");
				respContent = event;

				// 关注
				if (event.equals(EventType.EVENT_TYPE_SUBSCRIBE.getValue())) {
					respContent = "谢谢您的关注！";
				}
				// 取消关注
				else if (event.equals(EventType.EVENT_TYPE_UNSUBSCRIBE.getValue())) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (event.equals(EventType.EVENT_TYPE_SCAN.getValue())) {
					String eventKey = map.get("EventKey");
					if (Integer.valueOf(eventKey) == 22) {
						System.err.println("即将推文...");
						Article article = new Article();
						article.setUrl("http://cjh.nat300.top/index");
						article.setTiTle("扫码二维码推文");
						article.setPicUrl(
								"https://gss0.bdstatic.com/70cFfyinKgQIm2_p8IuM_a/daf/pic/item/4d086e061d950a7bf1ff583001d162d9f3d3c978.jpg");
						article.setDescription("详细描述");
						List<Article> list = new ArrayList<>();
						list.add(article);
						NewsMessage message = new NewsMessage();
						message.setArticleCount(1);
						message.setArticles(list);
						message.setToUserName(fromUserName);
						message.setFromUserName(toUserName);
						message.setCreateTime(new Date().getTime());
						message.setMsgType(MessageType.RESQ_MESSAGE_TYPE_NEWS.getValue());

						respXml = XmlUtil.messageToXml(message);
						return respXml;
					} else {
						String Ticket = map.get("Ticket");
						respContent += Ticket == null ? "" : Ticket;
					}
				}
				// 上报地理位置
				else if (event.equals(EventType.EVENT_TYPE_LOCATION.getValue())) {
				}
				// 自定义菜单
				else if (event.equals(EventType.EVENT_TYPE_CLICK.getValue())) {
					respContent += "\r\n" + UserUtil.getUserInfo(fromUserName);
				}
				// 自定义菜单-跳转
				else if (event.equals("VIEW")) {
					String EventKey = map.get("EventKey");
					respContent += EventKey;
				}
			}
			// respContent += "。。。点击了菜单";
			// 设置文本消息的内容
			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			respXml = XmlUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respXml;
	}

	// 根据标签进行群发
	private static String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

	/**
	 * 发送图文
	 * 
	 * @throws Exception
	 */
	public static void send(String media_id, boolean is_to_all, int tag_id) throws Exception {
		String url = sendUrl.replace("ACCESS_TOKEN", TokenUtil.getTokenInDB().getAccess_token());
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> filter = new HashMap<>();
		filter.put("is_to_all", is_to_all);
		filter.put("tag_id", tag_id);
		map.put("filter", filter);
		Map<String, Object> mpnews = new HashMap<>();
		// mpnews.put("media_id", media_id);
		// map.put("mpnews", mpnews);
		// map.put("msgtype", "mpnews");

		// mpnews.put("content", "hello text");
		// map.put("text", mpnews);
		// map.put("msgtype", "text");

		mpnews.put("media_id", media_id);
		map.put("image", mpnews);
		map.put("msgtype", "image");

		map.put("send_ignore_reprint", 0);
		String parms = getParmsJsonToMap(map);
		JSONObject respBody = HttpsUtil.httpRequest(url, MethodType.POST, parms);
		System.err.println(respBody);
	}

	private static String getParmsJsonToMap(Map<?, ?> map) {
		return JSONObject.fromObject(map).toString();
	}

}
