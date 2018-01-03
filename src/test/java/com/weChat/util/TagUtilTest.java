package com.weChat.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.weChat.bean.tag.Tag;
import com.weChat.bean.user.UserAndLang;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagUtilTest {

	@Test
	public void testCreateTag() throws Exception {
		System.out.println(TagUtil.createTag("解析jsonTag22"));
		;
	}

	@Test
	public void testGetTags() throws Exception {
		TagUtil.getTags();
	}

	@Test
	public void testPutTag() throws Exception {
		TagUtil.putTag(new Tag(100, "0/1/2的大佬？"));
	}

	@Test
	public void testDelTag() throws Exception {
		TagUtil.delTag(101);
	}

	/**
	 * get有问题。。 。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetUsersToTag() throws Exception {
		TagUtil.getUsersToTag(2, "ouTbH0aDYqhXvpwhcJ3i5sHfi8JY");
	}

	@Test
	public void testSetUsersToTag() throws Exception {
		TagUtil.setUsersToTag(new String[] { "ouTbH0aDYqhXvpwhcJ3i5sHfi8JY" }, 2);
	}

	@Test
	public void testRemoveUsersToTag() throws Exception {
		TagUtil.removeUsersToTag(new String[] { "ouTbH0aDYqhXvpwhcJ3i5sHfi8JY" }, 100);
	}

	@Test
	public void testGetTagsToUser() throws Exception {
		TagUtil.getTagsToUser("ouTbH0aDYqhXvpwhcJ3i5sHfi8JY");
	}

	@Test
	public void testSetRemarkUrl() throws Exception {
		UserUtil.setRemark("ouTbH0aDYqhXvpwhcJ3i5sHfi8JY", "大佬");
	}

	@Test
	public void testGetUserInfo() throws Exception {
		UserUtil.getUserInfo("ouTbH0aDYqhXvpwhcJ3i5sHfi8JY");
	}

	@Test
	public void testGetUsersInfo() throws Exception {
		System.out.println(
				UserUtil.getUsersInfo(new UserAndLang[] { new UserAndLang("ouTbH0aDYqhXvpwhcJ3i5sHfi8JY") }).size());
		;
	}

	@Test
	public void testGetUserList() throws Exception {
		UserUtil.getUserList(null);
	}

	@Test
	public void testGetBlackList() throws Exception {
		UserUtil.getBlackList("");
	}

	@Test
	public void testAddBlack() throws Exception {
		UserUtil.addBlack(new String[] {"ouTbH0aDYqhXvpwhcJ3i5sHfi8JY"});
	}

	@Test
	public void testRemoveBlack() throws Exception {
		UserUtil.removeBlack(new String[] {"ouTbH0aDYqhXvpwhcJ3i5sHfi8JY"});
	}
	
	@Test
	public void testSend() throws Exception {
		MessageUtil.send("sixfcujiMd-oVFpXMv_jsn_LPMgXYx2YvfP292O21QHgHwfUGcxkQ_nJR_EczvjH", false, 100);
	}

}
