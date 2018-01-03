package com.weChat.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.weChat.util.JSSKDUtil;

/**
 * JSSDK切面拦截初始化
 * 
 * @author 陈俊华
 * @date 2017年12月17日
 */
@Aspect
@Component
public class JSSDKAop {

	private final static Logger LOGGER = LoggerFactory.getLogger(JSSDKAop.class);

	/**
	 * 定义切点面拦截路径
	 */
	@Pointcut("execution(public * com.weChat.controller.JSSDK*.*(..))")
	public void log() {

	}

	/**
	 * 执行前，获取JSSDK签名
	 * 
	 * @param joinPoint
	 */
	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		// 获取上下文
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 获取签名sign
		try {
			Map<String, String> map = JSSKDUtil.getSignatureParms(request.getRequestURL().toString());
			for (String key : map.keySet()) {
				LOGGER.info("添加签名参数={}", key + ":" + map.get(key));
				request.setAttribute(key, map.get(key));
			}
		} catch (Exception e) {
			LOGGER.info("error={}", "获取签名getSignatureParms异常");
			e.printStackTrace();
		}

		// URL
		LOGGER.info("url={}", request.getRequestURL());
		// METHOD
		LOGGER.info("method={}", request.getMethod());
		// IP
		LOGGER.info("ip={}", request.getRemoteAddr() + "||" + request.getHeader("x-forwarded-for"));
		// 类方法
		LOGGER.info("class_method={}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		// 参数
		LOGGER.info("args={}", joinPoint.getArgs());
	}

	/**
	 * 执行后
	 */
	@After("log()")
	public void doAfter() {

	}

	/**
	 * 返回后
	 */
	@AfterReturning(pointcut = "log()", returning = "object")
	public void doAfterReTurning(Object object) {
		LOGGER.info("response={}", object == null ? null : object.toString());
	}

}
