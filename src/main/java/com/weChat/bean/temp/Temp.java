package com.weChat.bean.temp;

/**
 * 模板消息-bean
 * 
 * @author 陈俊华
 * @date 2017年12月21日
 */
public class Temp {

	private String touser;
	private String template_id;
	private String url;
	private String topcolor;
	private TemplateItem data;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public TemplateItem getData() {
		return data;
	}

	public void setData(TemplateItem data) {
		this.data = data;
	}

}
