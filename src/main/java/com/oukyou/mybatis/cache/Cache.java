/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/06.
 */
package com.oukyou.mybatis.cache;

/**
 * 缓存
 */
public interface Cache {
	/**
	 * 存入缓存
	 * 
	 * @param key 缓存key
	 * @param value 缓存值
	 */
	public void putObject(Object key, Object value);

	/**
	 * 取得指定key缓存值
	 * 
	 * @param key 缓存key
	 * @return 指定key缓存值
	 */
	public Object getObject(Object key);

	/**
	 * 清空缓存
	 */
	public void clear();
}