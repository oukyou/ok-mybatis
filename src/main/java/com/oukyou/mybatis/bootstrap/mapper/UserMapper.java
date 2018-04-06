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

	@Select("SELECT * FROM t_user WHERE id = ?")
	public User selectOne(int id);

	@Select("SELECT * FROM t_user WHERE name like concat('%', ? ,'%') escape '/'")
	public List<User> selectList(String name);

	@Update("UPDATE t_user SET age = ? WHERE name = ?")
	public int update(int age, String name);

	@Insert("INSERT INTO t_user (name, age) VALUES (?, ?)")
	public int insert(String name, int age);

	@Delete("DELETE FROM t_user WHERE name = ?")
	public int delete(String name);
}
