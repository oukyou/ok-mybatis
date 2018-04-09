/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/09.
 */
package com.oukyou.mybatis.plugins;

/**
 * 拦截器接口<br>
 * 自定义拦截插件时需要实现该接口
 */
public interface Interceptor {

	/**
	 * 实施拦截
	 * 
	 * @param invocation 调用方法情报
	 * @return 拦截结果
	 * @throws Throwable 处理异常
	 */
	public Object intercept(Invocation invocation) throws Throwable;

	/**
	 * 获取动态代理实例
	 * 
	 * @param target 调用元对象类
	 * @return 动态代理实例
	 */
	public Object getInstance(Object target);
}
