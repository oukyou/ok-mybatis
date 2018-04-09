/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/09.
 */
package com.oukyou.mybatis.plugins;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器链
 */
public final class InterceptorChain {

	/**
	 * 拦截器一览
	 */
	private final List<Interceptor> interceptors = new ArrayList<Interceptor>();

	/**
	 * 添加拦截器
	 * 
	 * @param interceptor 拦截器
	 */
	public void addInterceptor(Interceptor interceptor) {
		interceptors.add(interceptor);
	}

	/**
	 * 执行所有拦截<br>
	 * 当有多个拦截器时，会多层嵌套动态代理
	 * 
	 * @param target 调用元对象类
	 * @return 动态代理实现类
	 */
	public Object interceptorAll(Object target) {
		for (Interceptor interceptor : interceptors) {
			target = interceptor.getInstance(target);
		}

		return target;
	}

}
