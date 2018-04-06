/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/06.
 */
package com.oukyou.mybatis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库操作工具类
 */
public final class DBUtil {

	/**
	 * 数据库名
	 */
	private static final String DATABASE = PropertyUtil.getProperty("database");
	/**
	 * 数据库连接URL
	 */
	private static final String DATABASE_URL = PropertyUtil.getProperty("database.url");
	/**
	 * 数据库连接用户名
	 */
	private static final String DATABASE_USER_NAME = PropertyUtil.getProperty("database.username");
	/**
	 * 数据库连接密码
	 */
	private static final String DATABASE_PASSWORD = PropertyUtil.getProperty("database.password");

	/**
	 * 数据库连接驱动
	 */
	private static final String DATABASE_DRIVER_CLASS_NAME = PropertyUtil.getProperty("database.driverClassName");

	/**
	 * 私有构造函数
	 */
	private DBUtil() {
		// 什么都不做
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return 数据库连接
	 * @throws Exception 处理异常
	 */
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			// 加载驱动
			Class.forName(DATABASE_DRIVER_CLASS_NAME);

			// 获得连接
			connection = DriverManager.getConnection(DATABASE_URL + DATABASE, DATABASE_USER_NAME, DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取数据库连接失败。");
		}

		return connection;
	}

	/**
	 * 关闭操作
	 * 
	 * @param conn 数据库连接
	 * @param statement 语句处理
	 * @param resultSet 结果集
	 * @throws SQLException 处理异常
	 */
	public static void close(Connection conn, PreparedStatement statement, ResultSet resultSet) throws SQLException {
		try {
			if (conn != null) {
				conn.close();
			}

			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
