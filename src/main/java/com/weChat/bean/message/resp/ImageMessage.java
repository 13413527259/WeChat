package com.weChat.bean.message.resp;

/**
 * 响应信息-图片消息
 * 
 * @author 13413527259
 *
 */
public class ImageMessage extends BaseMessage {

	//响应的图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

	public ImageMessage(String toUserName, String fromUserName, long createTime, String msgType,
			com.weChat.bean.message.resp.Image image) {
		super(toUserName, fromUserName, createTime, msgType);
		Image = image;
	}

	public ImageMessage() {
	}

}
