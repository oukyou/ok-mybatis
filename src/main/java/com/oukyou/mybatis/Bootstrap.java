/*
 * Copyright(c) 2018 Oukyou.
 *
 * @author Oukyou
 * @version New 2018/04/05.
 */
package com.oukyou.mybatis;

import java.util.List;

import com.oukyou.mybatis.bootstrap.entity.User;
import com.oukyou.mybatis.bootstrap.mapper.UserMapper;
import com.oukyou.mybatis.configuration.Configuration;
import com.oukyou.mybatis.session.SqlSession;
import com.oukyou.mybatis.session.SqlSessionFactory;

/**
 * 测试类
 */
public final class Bootstrap {
	public static void main(String[] args) throws Exception {

		Configuration config = initConfiguration();

		SqlSession sqlSession = SqlSessionFactory.createSqlSession(config);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 查询一件
		User user = userMapper.selectOne(1);
		System.out.println(user);

		// 查询多件
		List<User> userList = userMapper.selectList("テスト");
		System.out.println(userList);

		//读取缓存
		user = userMapper.selectOne(1);
		System.out.println(user);

		// 插入数据库
		int insertResult = userMapper.insert("Oukyou", 31);
		System.out.println(insertResult);
		
		// 数据库执行了更新操作，缓存清除，重新检索数据库
		user = userMapper.selectOne(1);
		System.out.println(user);

		// 更新数据库
		int updateResult = userMapper.update(18, "Oukyou");
		System.out.println(updateResult);

		// 删除数据
		int deleteResult = userMapper.delete("Oukyou");
		System.out.println(deleteResult);
	}

	/**
	 * 初期化配置中心
	 * 
	 * @return 配置中心
	 * @throws Exception 处理异常
	 */
	private static Configuration initConfiguration() throws Exception {
		Configuration config = new Configuration();
		config.addMapper(UserMapper.class);

		return config;
	}
}
