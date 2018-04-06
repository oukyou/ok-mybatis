/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.executor;

import java.util.List;

/**
 * 执行器
 */
public interface Executor {

	/**
	 * 执行查询
	 * 
	 * @param sql sql文
	 * @param param 动态参数
	 * @param returnType 返回类型/返回类型的泛型类型
	 * @return 查询结果
	 */
	public <E> List<E> doQuery(String sql, Object param, Class<E> returnType);

	/**
	 * 执行更新<br>
	 * 包括数据库更新，插入，删除
	 * 
	 * @param sql sql文
	 * @param param 动态参数
	 * @return 更新行数
	 */
	public int doUpdate(String sql, Object param);
}
