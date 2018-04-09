/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/06.
 */
package com.oukyou.mybatis.executor;

import java.util.List;

import com.oukyou.mybatis.cache.Cache;
import com.oukyou.mybatis.cache.CacheImpl;

/**
 * 缓存执行器
 */
public final class CachingExecutor implements Executor {

	private static final String COMMA = ",";
	/**
	 * 简单执行器（装饰器模式）
	 */
	private final Executor simpleExecutor;

	/**
	 * 缓存
	 */
	private final Cache cache;

	/**
	 * 构造函数
	 * 
	 * @param simpleExecutor 简单执行器
	 */
	public CachingExecutor(Executor simpleExecutor) {
		this.simpleExecutor = simpleExecutor;
		this.cache = new CacheImpl();
	}

	/**
	 * @see Executor#doQuery(String, Object, Class)
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> doQuery(String sql, Object param, Class<E> returnType) {
		// 生成缓存KEY
		String key = createCacheKey(sql, param, returnType);
		// 缓存中存在，则返回缓存中值
		if (cache.getObject(key) != null) {
			System.out.print("返回缓存值: ");
			return (List<E>) cache.getObject(key);
		}

		// 否则执行数据库查询并存入缓存
		List<E> result = simpleExecutor.doQuery(sql, param, returnType);
		cache.putObject(key, result);

		return result;
	}

	/**
	 * @see Executor#doUpdate(String, Object)
	 */
	public int doUpdate(String sql, Object param) {
		int result = simpleExecutor.doUpdate(sql, param);
		cache.clear();
		return result;
	}

	/**
	 * 生成缓存key
	 * 
	 * @param sql sql文
	 * @param param 参数
	 * @param returnType 返回类型
	 * @return 缓存key
	 */
	private String createCacheKey(String sql, Object param, Class<?> returnType) {
		StringBuilder builder = new StringBuilder(sql);
		builder.append(COMMA);
		if (param != null) {
			Object[] objects = (Object[]) param;
			for (Object obj : objects) {
				builder.append(obj.getClass());
				builder.append(COMMA);
				builder.append(obj);
				builder.append(COMMA);
			}
		}
		builder.append(returnType.toString());
		return builder.toString();
	}

}
