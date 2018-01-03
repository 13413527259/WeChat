package com.weChat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.weChat.bean.temp.Item;
import com.weChat.bean.temp.Temp;
import com.weChat.bean.temp.TemplateItem;
import com.weChat.util.HttpsUtil;
import com.weChat.util.MenuUtil;
import com.weChat.util.TempUtil;
import com.weChat.util.TokenInfo;
import com.weChat.util.TokenUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatApplicationTests {

	// @Test
	public void contextLoads() throws Exception {
		TempUtil.getTempList();

		Temp temp = new Temp();
		temp.setTouser("ouTbH0aDYqhXvpwhcJ3i5sHfi8JY");
		temp.setUrl("http://baidu.com");
		temp.setTemplate_id("2hbq7NlPOWcbVBDlaTOMXtsPX9HP76HYvk0cWuLe5iY");
		temp.setTopcolor("#FF0000");
		TemplateItem templateItem = new TemplateItem();
		templateItem.put("first", new Item("捐赠爱心礼物成功！", "#0000FF"));
		templateItem.put("orderMoneySum", new Item("50", "#0000FF"));
		templateItem.put("orderProductName", new Item("一次描述", "#0000FF"));
		templateItem.put("Remark", new Item(
				"\n我们已收到您的捐款，开始为您打爱心物资，山区小朋友很快就能收到亲的爱心! \n\n如有问题请致电400-828-1878或直接在微信留言，小易将第一时间为您服务！", "#FC00E8"));
		temp.setData(templateItem);
		String content = TempUtil.tempToJsonStr(temp);
		System.out.println(content);
		TempUtil.send(content);
	}

	// @Test
	public void createQR() throws Exception {
		// QRCodeUtil.longToShort(QRCodeUtil.getQRCodeUrl(QRCodeUtil.createQRCode("index").getTicket()));
		// QRCodeUtil.longToShort("http://wap.koudaitong.com/v2/showcase/goods?alias=128wi9shh&spm=h56083&redirect_count=1");
		System.err.println(MenuUtil.createMenu(MenuUtil.buildMenu(),
				TokenUtil.getToken(TokenInfo.APPID.getValue(), TokenInfo.APPSECRET.getValue())));
	}

	@Test
	public void testUpload() throws Exception {
		System.out.println(HttpsUtil.uploadHttpRequest("https://api.weixin.qq.com/cgi-bin/media/upload?access_token="
				+ TokenUtil.getToken(TokenInfo.APPID.getValue(), TokenInfo.APPSECRET.getValue()).getAccess_token()
				+ "&type=voice", "C:\\\\阿里远程密码.png"));
		;
	}

}
