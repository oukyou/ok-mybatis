/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/06.
 */
package com.oukyou.mybatis.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存实现
 */
public final class CacheImpl implements Cache {

	/**
	 * 缓存键值对
	 */
	private Map<Object, Object> cacheMap = new HashMap<Object, Object>();

	/**
	 * @see Cache#putObject(Object, Object)
	 */
	public void putObject(Object key, Object value) {
		cacheMap.put(key, value);
	}

	/**
	 * @see Cache#getObject(Object)
	 */
	public Object getObject(Object key) {
		return cacheMap.get(key);
	}

	/**
	 * @see Cache#clear()
	 */
	public void clear() {
		cacheMap.clear();
	}

}
