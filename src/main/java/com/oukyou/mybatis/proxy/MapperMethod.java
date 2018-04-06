/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.proxy;

import java.util.Collection;

import com.oukyou.mybatis.configuration.MethodData;
import com.oukyou.mybatis.session.SqlSession;

/**
 * Mapper方法情报
 */
public final class MapperMethod {

	/**
	 * 方法数据
	 */
	private MethodData methodData;

	/**
	 * 构造函数
	 * 
	 * @param methodData 方法数据
	 */
	public MapperMethod(MethodData methodData) {
		this.methodData = methodData;
	}

	/**
	 * 方法执行
	 * 
	 * @param sqlSession sql会话
	 * @param args 动态参数
	 * @return 方法执行结果
	 * @throws Exception 处理异常
	 */
	public Object execute(SqlSession sqlSession, Object[] args) throws Exception {
		Object result;
		switch (methodData.getCommandType()) {
			case INSERT: {
				result = sqlSession.insert(methodData.getSql(), args);
				break;
			}
			case UPDATE: {
				result = sqlSession.update(methodData.getSql(), args);
				break;
			}
			case DELETE: {
				result = sqlSession.delete(methodData.getSql(), args);
				break;
			}
			case SELECT:
				// 判断是否是集合类返回类型如果是集合返回类型，需要将泛型类型传给执行器
				if (Collection.class.isAssignableFrom(methodData.getReturnType())) {
					result = sqlSession.selectList(methodData.getSql(), args, methodData.getGenericReturnType());
				} else {
					result = sqlSession.selectOne(methodData.getSql(), args, methodData.getReturnType());
				}
				break;
			default:
				throw new Exception("找不到执行方法: " + methodData.getCommandType().toString());
		}
		if (result == null && methodData.getReturnType().isPrimitive()) {
			throw new Exception("方法: " + methodData.getName() + " 返回结果位null。");
		}
		return result;
	}
}
