package com.weChat.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * 模拟请求-工具类
 * 
 * @author 陈俊华
 * @date 2017年12月14日
 */
public class HttpsUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpsUtil.class);

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
	public static String downHttpRequest(String url, String downPath) throws Exception {

		logger.info("url={}", url);
		// 建立连接
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);
		client.executeMethod(get);
		for (Header item : get.getResponseHeaders()) {
			System.err.println(item);
		}
		// 获取响应头的文件名字段
		String status = get.getResponseHeader("Content-Type").getValue().toString();
		if (status.contains("application/json")) {
			throw new Exception(get.getResponseBodyAsString());
		}
		String fileName = get.getResponseHeader("Content-disposition").getValue().toString();
		// 正则匹配文件名
		Matcher mc = Pattern.compile("filename=\"([^\"]+)\"").matcher(fileName);
		if (mc.find()) {
			fileName = mc.group(1);
		}
		String filePathAndName = downPath + fileName;
		logger.info("filePath={}", filePathAndName);
		InputStream in = get.getResponseBodyAsStream();
		OutputStream out = new FileOutputStream(new File(filePathAndName));
		int line = -1;
		while ((line = in.read()) != -1) {
			out.write(line);
		}
		out.close();
		in.close();
		// 这里用缓存流文件会变大，从而图片写入错误，原因未知
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(get.getResponseBodyAsStream()));
		// BufferedWriter bw = new BufferedWriter(new FileWriter(new File(downPath +
		// fileName)));
		// String line = null;
		// while ((line = br.readLine()) != null) {
		// bw.write(line + "\r\n");
		// bw.flush();
		// }
		// br.close();
		// bw.close();

		return filePathAndName;
	}

	/**
	 * 根据请求方法发起请求，获得响应结果
	 * 
	 * @param url
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
	public static JSONObject httpRequest(String url, MethodType methodName, String parms) throws Exception {

		logger.info("url={}", url);
		logger.info("methodName={}", methodName);
		logger.info("parms={}", parms);
		// 建立连接
		HttpClient client = new HttpClient();
		HttpMethod method = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		// 判断method
		switch (methodName) {
		case GET:
			GetMethod get = new GetMethod(url);
			 get.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			 get.setRequestHeader("Accept", "application/json; charset=UTF-8");
			client.executeMethod(get);
			// 上面的设置编码不起效，所以用了流的方法
			br = new BufferedReader(new InputStreamReader(get.getResponseBodyAsStream(), "utf-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return JSONObject.fromObject(sb.toString());
		case POST:
			// method = new PostMethod(url);
			PostMethod post = new PostMethod(url);
			// 设置请求内容类型、编码
			post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			// 设置客户端能接受的响应类型、编码
			post.setRequestHeader("Accept", "application/json;charset=UTF-8");
			// 设置请求主体
			RequestEntity requestEntity = new StringRequestEntity(parms);
			post.setRequestEntity(requestEntity);
			/*
			 * post.setRequestBody(parms);画横线的原因：已被post.setRequestEntity(requestEntity)代替
			 */
			client.executeMethod(post);
			br = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), "utf-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return JSONObject.fromObject(sb.toString());
		case PUT:
			method = new PutMethod(url);
			break;
		case DELETE:
			method = new DeleteMethod(url);
			break;
		default:
			throw new Exception("请检查请求方法");
		}
		// 发送请求
		client.executeMethod(method);
		// 封装JSON返回结果
		return JSONObject.fromObject(method.getResponseBodyAsString());
	}

	/**
	 * 上传文件
	 * 
	 * @param url
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static JSONObject uploadHttpRequest(String url, String filePath) throws Exception {
		logger.info("url={}", url);
		logger.info("filePath={}", filePath);
		File file = new File(filePath);
		if (!file.exists() || file.length() < 6) {
			throw new Exception("找不到文件");
		}
		// 构建请求
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		// 加入文件参数
		FilePart filePart = new FilePart(file.getName(), file);
		// 特别注意需要设置Content-Disposition: form-data; name=media
		filePart.setName("media");
		Part[] parts = { filePart };
		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
		// 请求超时
		client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		// 执行
		client.executeMethod(post);
		// 返回结果
		return JSONObject.fromObject(post.getResponseBodyAsString());
	}

}
