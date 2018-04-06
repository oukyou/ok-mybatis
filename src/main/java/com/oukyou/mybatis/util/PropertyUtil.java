/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/06.
 */
package com.oukyou.mybatis.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Property工具类
 */
public final class PropertyUtil {
	/**
	 * Property文件目录
	 */
	private static final String PROPERTY_DIR_PATH = "META-INF/mybatis/";
	/**
	 * Property文件后缀
	 */
	private static final String SUFFIX = ".properties";
	/**
	 * Property
	 */
	private static final Properties properties;

	/**
	 * 私有构造函数
	 */
	private PropertyUtil() {
		// 什么都不做
	}

	/**
	 * 加载Property
	 */
	static {
		properties = new Properties();
		String fileName = "";
		try {
			String urL = PropertyUtil.class.getClassLoader().getResource("").getPath();
			File dirFile = new File(urL, PROPERTY_DIR_PATH);

			for (File file : dirFile.listFiles()) {
				fileName = file.getName();
				if (fileName.endsWith(SUFFIX)) {
					properties.load(Files.newBufferedReader(Paths.get(file.getPath()), StandardCharsets.UTF_8));
				}
			}

		} catch (IOException e) {
			// 文件读取失败
			throw new RuntimeException(String.format("文件读取失败:%s", fileName));
		}
	}

	/**
	 * 取值指定key的值
	 *
	 * @param key key
	 * @return 值
	 */
	public static String getProperty(final String key) {
		return getProperty(key, "");
	}

	/**
	 * 取值指定key的值
	 *
	 * @param key key
	 * @param defaultValue 默认值
	 * @return key不存在的时候返回默认值，否则返回key对应的值
	 */
	public static String getProperty(final String key, final String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
}
