package com.weChat.bean.message.req;

/**
 * 请求消息-语音消息
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class VoiceMessage extends BaseMessage {

	// 媒体Id
	private String MediaId;
	// 语音格式
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

}
