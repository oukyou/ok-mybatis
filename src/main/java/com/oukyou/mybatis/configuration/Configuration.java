/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.configuration;

import java.lang.reflect.Proxy;

import com.oukyou.mybatis.executor.CachingExecutor;
import com.oukyou.mybatis.executor.Executor;
import com.oukyou.mybatis.executor.SimpleExecutor;
import com.oukyou.mybatis.plugins.Interceptor;
import com.oukyou.mybatis.plugins.InterceptorChain;
import com.oukyou.mybatis.proxy.MapperProxy;
import com.oukyou.mybatis.session.SqlSession;

/**
 * 配置中心
 */
public final class Configuration {

	/**
	 * 拦截器链
	 */
	private final InterceptorChain interceptorChain = new InterceptorChain();

	/**
	 * 一级缓存开启
	 */
	private boolean cacheEnabled = true;
	/**
	 * Mapper注冊器
	 */
	private final MapperRegistry mapperRegistry = new MapperRegistry();

	/**
	 * 获取Mapper注冊器
	 * 
	 * @return Mapper注冊器
	 */
	public MapperRegistry getMapperRegistry() {
		return mapperRegistry;
	}

	/**
	 * 添加Mapper类
	 * 
	 * @param clazz Mapper类
	 * @throws Exception 处理失败
	 */
	public void addMapper(Class<?> clazz) throws Exception {
		mapperRegistry.addMapper(clazz);
	}

	/**
	 * 动态生成对象Mapper类的代理类
	 * 
	 * @param clazz Mapper类
	 * @param sqlSession SQL会话
	 * @return 对象Mapper类的代理类
	 */
	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<?> clazz, SqlSession sqlSession) {
		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { clazz },
				new MapperProxy(clazz.getName(), sqlSession));
	}

	/**
	 * 生成执行器
	 * 
	 * @return 执行器
	 */
	public Executor newExecutor() {
		// 默认简单执行器
		Executor executor = new SimpleExecutor();

		// 一级缓存
		if (cacheEnabled) {
			// 缓存执行器
			executor = new CachingExecutor(executor);
		}

		//
		executor = (Executor) interceptorChain.interceptorAll(executor);
		return executor;
	}

	/**
	 * 一级缓存是否开启
	 * 
	 * @return true(开启)/false(关闭)
	 */
	public boolean isCacheEnabled() {
		return cacheEnabled;
	}

	/**
	 * 设置一级缓存是否开启
	 * 
	 * @param cacheEnabled true(开启)/false(关闭)
	 */
	public void setCacheEnabled(boolean cacheEnabled) {
		this.cacheEnabled = cacheEnabled;
	}

	/**
	 * 添加拦截器
	 * 
	 * @param interceptor
	 */
	public void addInterceptor(Interceptor interceptor) {
		interceptorChain.addInterceptor(interceptor);
	}
}
