/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/09.
 */
package com.oukyou.mybatis.bootstrap.plugin;

import com.oukyou.mybatis.executor.Executor;
import com.oukyou.mybatis.plugins.Interceptor;
import com.oukyou.mybatis.plugins.InterceptorProxy;
import com.oukyou.mybatis.plugins.Intercepts;
import com.oukyou.mybatis.plugins.Invocation;
import com.oukyou.mybatis.plugins.Signature;

/**
 * 执行器拦截插件<br>
 * 拦截更新方法
 */
@Intercepts({ @Signature(type = Executor.class, method = "doUpdate", args = { String.class, Object.class }) })
public class ExecutorUpdatePlugin implements Interceptor {
	/**
	 * @see Interceptor#intercept(Invocation)
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		String sql = (String) invocation.getArgs()[0];
		System.out.println(String.format("执行更新拦截器 方法 = %s , sql=%s,", invocation.getMethod(), sql));
		return invocation.proceed();
	}

	/**
	 * @see Interceptor#getInstance(Object)
	 */
	public Object getInstance(Object target) {
		try {
			return InterceptorProxy.newInstance(target, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}
}
