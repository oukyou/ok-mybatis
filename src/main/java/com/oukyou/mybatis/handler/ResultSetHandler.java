/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/06.
 */
package com.oukyou.mybatis.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 结果集处理器
 */
public final class ResultSetHandler {

	/**
	 * int类型字符串
	 */
	private static final String STR_INT = "int";
	/**
	 * double类型字符串
	 */
	private static final String STR_DOUBLE = "double";

	/**
	 * 处理查询结果并返回
	 * 
	 * @param resultSet 结果集
	 * @param returnType 返回类型
	 * @return 返回查询结果
	 */
	public <E> List<E> handle(ResultSet resultSet, Class<E> returnType) {
		List<E> result = new ArrayList<E>();
		try {
			while (resultSet.next()) {
				// 创建返回类型的实例
				E instance = returnType.newInstance();

				// 获取每个属性，并且调用属性的set方法进行设值
				for (Field field : instance.getClass().getDeclaredFields()) {
					Method setMethod = instance.getClass().getMethod(getSetMethodName(field.getName()),
							field.getType());
					setMethod.invoke(instance, getFieldValue(resultSet, field));
				}
				result.add(instance);
			}
		} catch (Exception e) {
			throw new RuntimeException("数据绑定失败。");
		}
		return result;
	}

	/**
	 * 获取属性名的set方法名
	 * 
	 * @param name 属性名
	 * @return 属性名的set方法名
	 */
	private String getSetMethodName(String name) {
		char[] strChar = name.toCharArray();
		strChar[0] -= 32;
		return "set" + String.valueOf(strChar);
	}

	/**
	 * 获取结果集中的字段值
	 * 
	 * @param resultSet 结果集
	 * @param field 字段
	 * @return
	 * @throws SQLException
	 */
	private Object getFieldValue(ResultSet resultSet, Field field) throws SQLException {
		Object result = null;

		if (Integer.class.equals(field.getType()) || STR_INT.equals(field.getType().getName())) {
			// Integer或者int类型
			result = resultSet.getInt(field.getName());
		} else if (String.class.equals(field.getType())) {
			// String类型
			result = resultSet.getString(field.getName());
		} else if (Double.class.equals(field.getType()) || STR_DOUBLE.equals(field.getType().getName())) {
			// Double或者double类型
			result = resultSet.getDouble(field.getName());
		} else {
			// 其他数据类型
		}

		return result;
	}
}
