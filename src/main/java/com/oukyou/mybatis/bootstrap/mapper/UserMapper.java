/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis.bootstrap.mapper;

import java.util.List;

import com.oukyou.mybatis.annotations.Delete;
import com.oukyou.mybatis.annotations.Insert;
import com.oukyou.mybatis.annotations.Mapper;
import com.oukyou.mybatis.annotations.Select;
import com.oukyou.mybatis.annotations.Update;
import com.oukyou.mybatis.bootstrap.entity.User;

/**
 * 用户数据库操作接口
 */
@Mapper
public interface UserMapper {

	/**
	 * 通过id查询用户
	 * 
	 * @param id ID
	 * @return 用户
	 */
	@Select("SELECT * FROM t_user WHERE id = ?")
	public User selectOne(int id);

	/**
	 * 查询多个用户
	 * 
	 * @param name 名字
	 * @return 多个用户
	 */
	@Select("SELECT * FROM t_user WHERE name like concat('%', ? ,'%') escape '/'")
	public List<User> selectList(String name);

	/**
	 * 更新用户
	 * 
	 * @param age 年龄
	 * @param name 名字
	 * @return 更新结果行数
	 */
	@Update("UPDATE t_user SET age = ? WHERE name = ?")
	public int update(int age, String name);

	/**
	 * 插入新用户
	 * 
	 * @param age 年龄
	 * @param name 名字
	 * @return 插入结果行数
	 */
	@Insert("INSERT INTO t_user (name, age) VALUES (?, ?)")
	public int insert(String name, int age);

	/**
	 * 删除用户
	 * 
	 * @param name 名字
	 * @return 删除行数
	 */
	@Delete("DELETE FROM t_user WHERE name = ?")
	public int delete(String name);
}
