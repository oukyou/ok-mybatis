/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.executor;

import java.sql.Connection;
import java.util.List;

import com.oukyou.mybatis.handler.StatementHandler;
import com.oukyou.mybatis.util.DBUtil;

/**
 * 简单执行器
 */
public final class SimpleExecutor implements Executor {

	/**
	 * @see Executor#doQuery(String, Object, Class)
	 */
	public <E> List<E> doQuery(String sql, Object param, Class<E> returnType) {
		Connection conn = null;
		try {
			// 获取数据库连接
			conn = DBUtil.getConnection();
			StatementHandler handler = new StatementHandler();
			// 通过StatementHandler执行并返回结果
			return handler.executeQuery(conn, sql, param, returnType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("数据库操作失败。");
		}
	}

	/**
	 * @see Executor#doUpdate(String, Object)
	 */
	public int doUpdate(String sql, Object param) {
		int effectResult = 0;
		Connection conn = null;
		try {
			// 获取数据库连接
			conn = DBUtil.getConnection();
			StatementHandler handler = new StatementHandler();
			// 通过StatementHandler执行并返回结果
			effectResult = handler.executeUpdate(conn, sql, param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("数据库操作失败。");
		}

		return effectResult;
	}

	public String createCacheKey() {
		
		return null;
	}
}
