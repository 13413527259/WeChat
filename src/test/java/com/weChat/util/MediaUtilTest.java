package com.weChat.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MediaUtilTest {

	@Test
	public void testUploadInterim() throws Exception {
		MediaUtil.uploadInterim("C:\\1.jpg");
	}

	@Test
	public void testGetInterim() throws Exception {
		MediaUtil.getInterim("ymTHxrWivDes4EMYxVUrE6sep60_6Z4CAjtLTBIvtZ_RQhkybIjroEnsShIPvZxP");
	}

	@Test
	public void testUploadNewsImg() throws Exception {
		MediaUtil.uploadNewsImg("C:\\1.jpg");
	}

	@Test
	public void testNewsBuildToString() {
		String news = MediaUtil.NewsBuildToString("上传永久图文", "L1KyolEv_z8HXG5IUIeJWu-UIkklSKiTpjvoiAVVbAY", "陈俊华",
				"这是描述", 1, "<h1>我是内容</h1>", "https://www.baidu.com");
		System.out.println(news);
	}

	@Test
	public void testUploadNews() throws Exception {
		String news = MediaUtil.NewsBuildToString("上传永久图文", "L1KyolEv_z8HXG5IUIeJWu-UIkklSKiTpjvoiAVVbAY", "陈俊华",
				"这是描述", 1, "<h1>我是内容</h1>", "https://www.baidu.com");
		System.out.println(news);
		MediaUtil.uploadNews(news);
	}

	@Test
	public void testUploadForever() throws Exception {
		MediaUtil.uploadForever("C:\\1.jpg");
	}

	@Test
	public void testGetForever() throws Exception {
		// 之前40007是因为，没加""
		MediaUtil.getForever("L1KyolEv_z8HXG5IUIeJWu-UIkklSKiTpjvoiAVVbAY");
	}

	@Test
	public void testDelForever() throws Exception {
		MediaUtil.delForever("L1KyolEv_z8HXG5IUIeJWpRWcZW-yF20Xby5rxTUJ9A");
	}

	@Test
	public void testUpdateNews() throws Exception {
		String news = MediaUtil.NewsBuildToString("修改图文标题", "L1KyolEv_z8HXG5IUIeJWu-UIkklSKiTpjvoiAVVbAY", "陈俊华",
				"这是描述", 1, "<h1>我是内容</h1>", "https://www.baidu.com", "L1KyolEv_z8HXG5IUIeJWr59sVhR3mRtyRGZ3T2juPA", 0);
		System.out.println(news);
		MediaUtil.updateNews(news);
	}

	@Test
	public void testGetCount() throws Exception {
		MediaUtil.getCount();
	}

	@Test
	public void testGetList() throws Exception {
		MediaUtil.getList("image", 0, 5);
	}

}
