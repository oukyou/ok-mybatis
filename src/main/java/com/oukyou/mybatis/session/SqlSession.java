/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.session;

import java.util.List;

import com.oukyou.mybatis.configuration.Configuration;

/**
 * sql会话
 */
public interface SqlSession {

	/**
	 * 检索一行数据
	 * 
	 * @param sql sql文
	 * @param param 动态参数
	 * @param returnType 返回类型
	 * @return 一行数据
	 */
	public <E> E selectOne(String sql, Object param, Class<E> returnType);

	/**
	 * 检索多行数据
	 * 
	 * @param sql sql文
	 * @param param 动态参数
	 * @param returnType 返回类型
	 * @return 一行数据
	 */
	public <E> List<E> selectList(String sql, Object param, Class<E> returnType);

	/**
	 * 更新数据
	 * 
	 * @param sql sql文
	 * @param param 动态参数
	 * @return 执行结果
	 */
	public int update(String sql, Object param);

	/**
	 * 插入数据
	 * 
	 * @param sql sql文
	 * @param param 动态参数
	 * @return 执行结果
	 */
	public int insert(String sql, Object param);

	/**
	 * 删除数据
	 * 
	 * @param sql sql文
	 * @param param 动态参数
	 * @return 执行结果
	 */
	public int delete(String sql, Object param);

	/**
	 * 获取指定Mapper类
	 * 
	 * @param type Mapper类class
	 * @return 指定Mapper类的代理类
	 */
	public <T> T getMapper(Class<?> type);

	/**
	 * 获取 配置中心
	 * 
	 * @return 配置中心
	 */
	public Configuration getConfiguration();

}
