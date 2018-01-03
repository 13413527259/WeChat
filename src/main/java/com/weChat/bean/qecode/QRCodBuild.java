package com.weChat.bean.qecode;

import java.util.Map;

/**
 * 创建二维码的的到ticket需要的属性-bean
 * 
 * @author 陈俊华
 * @date 2017年12月21日
 */
@SuppressWarnings("rawtypes")
public class QRCodBuild {

	private String action_name;// 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值

	private Integer expire_seconds;// 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。

	private Map<String, Map> action_info;// 二维码详细信息

	public int getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public Map<String, Map> getAction_info() {
		return action_info;
	}

	public void setAction_info(Map<String, Map> action_info) {
		this.action_info = action_info;
	}

	public QRCodBuild() {
	}

	public QRCodBuild(String action_name, Map<String, Map> action_info) {
		this.action_name = action_name;
		this.action_info = action_info;
	}

	public QRCodBuild(int expire_seconds, String action_name, Map<String, Map> map) {
		this.expire_seconds = expire_seconds;
		this.action_name = action_name;
		this.action_info = map;
	}

}
