package com.weChat.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.weChat.bean.menu.BaseButton;
import com.weChat.bean.menu.ItemButton;
import com.weChat.bean.menu.Menu;
import com.weChat.bean.menu.ParentButton;
import com.weChat.bean.token.Token;

import net.sf.json.JSONObject;

/**
 * 自定义菜单-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月14日
 */
@Component
public class MenuUtil {

	private static String domain;

	/**
	 * 不能静态注入，set自动生产的static也要去掉
	 * 
	 * @param domain
	 */
	@Value("${domain}")
	public void setDomain(String domain) {
		MenuUtil.domain = domain;
	}

	public static String getDomain() {
		return domain;
	}

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 根据接口创建菜单
	 * 
	 * @param menu
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static int createMenu(Menu menu, Token token) throws Exception {
		String url = menu_create_url.replace("ACCESS_TOKEN", token.getAccess_token());
		System.err.println(url);
		JSONObject jsonObject = HttpsUtil.httpRequest(url, MethodType.POST, JSONObject.fromObject(menu).toString());
		return jsonObject.getInt("errcode");
	}

	/**
	 * 构造菜单
	 * 
	 * @return
	 */
	public static Menu buildMenu() {

		Menu menu = new Menu();

		ParentButton parent1 = new ParentButton();
		parent1.setName("lefe");
		ItemButton sub11 = new ItemButton();
		sub11.setName("微信发图");
		sub11.setKey("pic");
		sub11.setType("pic_photo_or_album");
		ItemButton sub12 = new ItemButton();
		sub12.setName("百度");
		sub12.setKey("baidu");
		sub12.setType("view");
		sub12.setUrl("http://www.baidu.com");
		ItemButton sub13 = new ItemButton();
		sub13.setName("我的信息");
		sub13.setKey("zan+1");
		sub13.setType("click");
		ItemButton sub14 = new ItemButton();
		sub14.setName("测试授权登录");
		sub14.setKey("zan+1");
		sub14.setType("view");
		sub14.setUrl(domain + "/toLogin");
		parent1.setSub_button(new BaseButton[] { sub12, sub11, sub13, sub14 });

		ParentButton parent2 = new ParentButton();
		parent2.setName("按钮类型");
		ItemButton sub21 = new ItemButton();
		sub21.setName("位置");
		sub21.setKey("localtion");
		sub21.setType("location_select");
		ItemButton sub22 = new ItemButton();
		sub22.setName("扫一扫");
		sub22.setKey("scan");
		sub22.setType("scancode_push");
		parent2.setSub_button(new BaseButton[] { sub22, sub21 });

		ParentButton parent3 = new ParentButton();
		parent3.setName("更多");
		ItemButton sub32 = new ItemButton();
		sub32.setName("scan");
		sub32.setKey("scan");
		sub32.setType("view");
		sub32.setUrl(domain + "/scan");
		ItemButton sub31 = new ItemButton();
		sub31.setName("QR");
		sub31.setKey("QR");
		sub31.setType("view");
		sub31.setUrl(domain + "/getQRCode");
		parent3.setSub_button(new BaseButton[] { sub31, sub32 });

		menu.setButton(new BaseButton[] { parent1, parent2, parent3 });

		return menu;

	}

	public static void main(String[] args) throws Exception {

		System.err.println(createMenu(buildMenu(),
				TokenUtil.getToken(TokenInfo.APPID.getValue(), TokenInfo.APPSECRET.getValue())));
	}

}
