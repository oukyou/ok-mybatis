/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/06.
 */
package com.oukyou.mybatis.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oukyou.mybatis.util.DBUtil;

/**
 * 语句处理器
 */
public final class StatementHandler {

	/**
	 * 执行查询
	 * 
	 * @param conn 数据库连接
	 * @param sql sql文
	 * @param param 动态参数
	 * @param returnType 返回类型
	 * @return 查询结果
	 */
	public <E> List<E> executeQuery(Connection conn, String sql, Object param, Class<E> returnType) {
		List<E> result = new ArrayList<E>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.prepareStatement(sql);
			if (param != null) {
				// 设置SQL参数
				Object[] objects = (Object[]) param;
				int index = 1;
				for (Object obj : objects) {
					statement.setObject(index++, obj);
					// 引用类型参数需要另作处理
				}
			}

			resultSet = statement.executeQuery();
			result = new ResultSetHandler().handle(resultSet, returnType);
		} catch (SQLException e) {
			throw new RuntimeException("数据库操作失败。");
		} finally {
			try {
				DBUtil.close(conn, statement, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("数据库操作失败。");
			}
		}

		return result;
	}

	/**
	 * 执行数据库更新
	 * 
	 * @param conn 数据库连接
	 * @param sql sql文
	 * @param param 动态参数
	 * @return 结果行数
	 */
	public int executeUpdate(Connection conn, String sql, Object param) {
		int result = 0;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
			if (param != null) {
				Object[] objects = (Object[]) param;
				int index = 1;
				for (Object obj : objects) {
					statement.setObject(index++, obj);
					// 引用类型参数需要另作处理
				}
			}

			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("数据库操作失败。");
		} finally {
			try {
				DBUtil.close(conn, statement, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
