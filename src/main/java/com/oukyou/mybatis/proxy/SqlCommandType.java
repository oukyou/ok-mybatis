/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.proxy;

/**
 * 数据库操作类型
 */
public enum SqlCommandType {
	/**
	 * 不明
	 */
	UNKNOWN,
	/**
	 * 插入
	 */
	INSERT,
	/**
	 * 更新
	 */
	UPDATE,
	/**
	 * 删除
	 */
	DELETE,
	/**
	 * 检索
	 */
	SELECT;
}
