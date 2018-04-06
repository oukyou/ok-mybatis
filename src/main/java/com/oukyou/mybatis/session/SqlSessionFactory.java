/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.session;

import com.oukyou.mybatis.configuration.Configuration;

/**
 * sql会话工厂
 */
public final class SqlSessionFactory {

	/**
	 * 私有构造函数
	 */
	private SqlSessionFactory() {
		// 什么都不做
	}

	/**
	 * 创建sql会话工厂
	 * 
	 * @param configuration 配置中心
	 * @param executor 执行器
	 * @return sql会话
	 */
	public static SqlSession createSqlSession(Configuration configuration) {
		return new SqlSessionImpl(configuration);
	}
}
