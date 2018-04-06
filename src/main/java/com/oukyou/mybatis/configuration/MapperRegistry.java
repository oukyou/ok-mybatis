/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.configuration;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.oukyou.mybatis.annotations.Delete;
import com.oukyou.mybatis.annotations.Insert;
import com.oukyou.mybatis.annotations.Mapper;
import com.oukyou.mybatis.annotations.Select;
import com.oukyou.mybatis.annotations.Update;
import com.oukyou.mybatis.proxy.SqlCommandType;

/**
 * Mapper注册器
 */
public final class MapperRegistry {
	/**
	 * 配置情报Map
	 */
	private Map<String, MapperData> configMap = new HashMap<String, MapperData>();;

	/**
	 * 获取配置情报Map
	 * 
	 * @return 配置情报Map
	 */
	public Map<String, MapperData> getConfigMap() {
		return configMap;
	}

	/**
	 * 添加Mapper类
	 * 
	 * @param clazz Mapper类
	 * @throws Exception 处理异常
	 */
	public void addMapper(Class<?> clazz) throws Exception {
		// Mapper类必须是接口类型 并且用Mapper注解
		if (clazz.isInterface() && clazz.isAnnotationPresent(Mapper.class)) {
			MapperData mapperData = new MapperData(clazz.getName());
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				// 获取返回值类型
				Class<?> returnType = method.getReturnType();
				Class<?> genericReturnType = null;
				if (Collection.class.isAssignableFrom(returnType)) {
					// 获取返回值的类型，此处不是数组
					Type type = method.getGenericReturnType();
					// 判断是否是泛型
					if (type instanceof ParameterizedType) {
						// 获取返回值的泛型参数
						Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
						genericReturnType = (Class<?>) actualTypeArguments[0];
					}
				}

				// 保存每个方法数据
				MethodData methodData;
				if (method.isAnnotationPresent(Select.class)) {
					methodData = new MethodData(method.getName(), method.getAnnotation(Select.class).value(),
							SqlCommandType.SELECT, returnType, genericReturnType);
				} else if (method.isAnnotationPresent(Update.class)) {
					methodData = new MethodData(method.getName(), method.getAnnotation(Update.class).value(),
							SqlCommandType.UPDATE, returnType, genericReturnType);
				} else if (method.isAnnotationPresent(Insert.class)) {
					methodData = new MethodData(method.getName(), method.getAnnotation(Insert.class).value(),
							SqlCommandType.INSERT, returnType, genericReturnType);
				} else if (method.isAnnotationPresent(Delete.class)) {
					methodData = new MethodData(method.getName(), method.getAnnotation(Delete.class).value(),
							SqlCommandType.DELETE, returnType, genericReturnType);
				} else {
					throw new Exception("没有定义数据库操作类型。");
				}
				
				mapperData.addMethodData(methodData);
			}

			configMap.put(clazz.getName(), mapperData);
		} else {
			throw new Exception("这不是一个Mapper。");
		}
	}
}
