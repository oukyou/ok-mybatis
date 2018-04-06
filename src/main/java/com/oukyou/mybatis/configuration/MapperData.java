/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper类数据
 */
public final class MapperData {
	/**
	 * 命名空间
	 */
	private String namespace;

	/**
	 * 方法数据Map
	 */
	private Map<String, MethodData> methodDataMap = new HashMap<String, MethodData>();

	/**
	 * 构造函数
	 * 
	 * @param namespace 命名空间
	 */
	public MapperData(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * 添加方法数据
	 * 
	 * @param methodData 方法数据
	 */
	public void addMethodData(MethodData methodData) {
		methodDataMap.put(methodData.getName(), methodData);
	}

	/**
	 * 获取命名空间
	 * 
	 * @return 命名空间
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * 获取方法数据
	 * 
	 * @return 方法数据
	 */
	public Map<String, MethodData> getMethodDataMap() {
		return methodDataMap;
	}
}
