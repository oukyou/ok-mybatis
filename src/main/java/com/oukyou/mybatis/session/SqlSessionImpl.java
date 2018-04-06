/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.session;

import java.util.List;

import com.oukyou.mybatis.configuration.Configuration;
import com.oukyou.mybatis.executor.Executor;

/**
 * sql会话实现类
 */
public final class SqlSessionImpl implements SqlSession {

	/**
	 * 配置中心
	 */
	private Configuration configuration;
	/**
	 * 执行器
	 */
	private Executor executor;

	/**
	 * 构造函数
	 * 
	 * @param configuration 配置中心
	 */
	public SqlSessionImpl(Configuration configuration) {
		this.configuration = configuration;
		this.executor = configuration.newExecutor();
	}

	/**
	 * @see SqlSession#selectOne(String, Object, Class)
	 */
	public <E> E selectOne(String sql, Object param, Class<E> returnType) {
		List<E> list = selectList(sql, param, returnType);
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * @see SqlSession#selectList(String, Object, Class)
	 */
	public <E> List<E> selectList(String sql, Object param, Class<E> returnType) {
		return executor.doQuery(sql, param, returnType);
	}

	/**
	 * @see SqlSession#update(String, Object)
	 */
	public int update(String sql, Object param) {
		return executor.doUpdate(sql, param);
	}

	/**
	 * @see SqlSession#insert(String, Object)
	 */
	public int insert(String sql, Object param) {
		return executor.doUpdate(sql, param);
	}

	/**
	 * @see SqlSession#delete(String, Object)
	 */
	public int delete(String sql, Object param) {
		return executor.doUpdate(sql, param);
	}

	/**
	 * @see SqlSession#getMapper(Class)
	 */
	public <T> T getMapper(Class<?> type) {
		return configuration.getMapper(type, this);
	}

	/**
	 * @see SqlSession#getConfiguration()
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

}
