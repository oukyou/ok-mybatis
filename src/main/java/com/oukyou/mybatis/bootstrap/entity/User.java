/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.bootstrap.entity;

/**
 * 用户
 */
public final class User {
	/**
	 * ID
	 */
	private int id;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 年龄
	 */
	private int age;

	/**
	 * 获取ID
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取名字
	 * 
	 * @return 名字
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名字
	 * 
	 * @param name 名字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取年龄
	 * 
	 * @return 年龄
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 设置年龄
	 * 
	 * @param age 年龄
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @see String#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
