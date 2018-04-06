/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.configuration;

import com.oukyou.mybatis.proxy.SqlCommandType;

/**
 * 方法数据
 */
public final class MethodData {
	/**
	 * 方法名
	 */
	private String name;
	/**
	 * 数据库操作类型
	 */
	private SqlCommandType commandType;
	/**
	 * sql文
	 */
	private String sql;

	/**
	 * 返回类型
	 */
	private Class<?> returnType;

	/**
	 * 返回类型的泛型类型
	 */
	private Class<?> genericReturnType;

	/**
	 * 构造函数
	 * 
	 * @param name 方法名
	 * @param sql sql文
	 * @param commandType 数据库操作类型
	 * @param returnType 返回类型
	 * @param genericReturnType 返回类型的泛型类型
	 */
	public MethodData(String name, String sql, SqlCommandType commandType, Class<?> returnType,
			Class<?> genericReturnType) {
		this.name = name;
		this.sql = sql;
		this.commandType = commandType;
		this.returnType = returnType;
		this.genericReturnType = genericReturnType;
	}

	/**
	 * 获取方法名
	 * 
	 * @return 方法名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取 数据库操作类型
	 * 
	 * @return 数据库操作类型
	 */
	public SqlCommandType getCommandType() {
		return commandType;
	}

	/**
	 * 获取 SQL文
	 * 
	 * @return SQL文
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * 获取返回类型
	 * 
	 * @return 返回类型
	 */
	public Class<?> getReturnType() {
		return returnType;
	}

	/**
	 * 获取返回类型的泛型类型
	 * 
	 * @return 返回类型的泛型类型
	 */
	public Class<?> getGenericReturnType() {
		return genericReturnType;
	}

}