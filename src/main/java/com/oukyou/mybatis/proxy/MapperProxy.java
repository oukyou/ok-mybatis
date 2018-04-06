/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.oukyou.mybatis.configuration.MapperData;
import com.oukyou.mybatis.configuration.MethodData;
import com.oukyou.mybatis.session.SqlSession;

/**
 * Mapper类代理
 */
public final class MapperProxy implements InvocationHandler {

	/**
	 * 命名空间
	 */
	private String namespace;
	/**
	 * sql会话
	 */
	private SqlSession sqlSession;

	/**
	 * 构造函数
	 * 
	 * @param namespace 命名空间
	 * @param sqlSession sql会话
	 */
	public MapperProxy(String namespace, SqlSession sqlSession) {
		this.namespace = namespace;
		this.sqlSession = sqlSession;
	}

	/**
	 * @see InvocationHandler#invoke(Object, Method, Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		try {
			// 获取指定命名空间的Mapper类情报
			MapperData mapperData = sqlSession.getConfiguration().getMapperRegistry().getConfigMap().get(namespace);
			// 获取要执行的方法的情报
			MethodData methodData = mapperData.getMethodDataMap().get(method.getName());

			if (methodData != null) {
				MapperMethod mapperMethod = new MapperMethod(methodData);
				return mapperMethod.execute(sqlSession, params);
			} else {
				return method.invoke(proxy, params);
			}
		} catch (Throwable t) {
			throw t;
		}
	}
}
