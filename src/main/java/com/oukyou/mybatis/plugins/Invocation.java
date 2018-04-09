/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/09.
 */
package com.oukyou.mybatis.plugins;

import java.lang.reflect.Method;

/**
 * 调用方法情报
 */
public final class Invocation {

	/**
	 * 调用元对象类
	 */
	private final Object target;
	/**
	 * 执行方法
	 */
	private final Method method;
	/**
	 * 方法参数
	 */
	private final Object[] args;

	/**
	 * 构造函数
	 * 
	 * @param target 调用元对象类
	 * @param method 执行方法
	 * @param args 方法参数
	 */
	public Invocation(Object target, Method method, Object[] args) {
		this.target = target;
		this.method = method;
		this.args = args;
	}

	/**
	 * 获取调用元对象类
	 * 
	 * @return 调用元对象类
	 */
	public Object getTarget() {
		return target;
	}

	/**
	 * 获取执行方法
	 * 
	 * @return 执行方法
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * 获取方法参数
	 * 
	 * @return 方法参数
	 */
	public Object[] getArgs() {
		return args;
	}

	/**
	 * 原本的方法代理执行
	 * 
	 * @return 方法处理结果
	 * @throws Exception 处理例外
	 */
	public Object proceed() throws Exception {
		return method.invoke(target, args);
	}

}
