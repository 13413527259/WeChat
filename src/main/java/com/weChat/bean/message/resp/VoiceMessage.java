package com.weChat.bean.message.resp;

/**
 * 响应消息-语音消息
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class VoiceMessage extends BaseMessage{

	//响应的语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

}
