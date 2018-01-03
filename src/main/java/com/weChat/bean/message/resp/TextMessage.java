package com.weChat.bean.message.resp;

/**
 * 响应信息-文本消息
 * 
 * @author 13413527259
 *
 */
public class TextMessage extends BaseMessage {

	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public TextMessage(String toUserName, String fromUserName, long createTime, String msgType, String content) {
		super(toUserName, fromUserName, createTime, msgType);
		Content = content;
	}

	public TextMessage() {
	}

}
