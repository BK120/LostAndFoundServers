package com.lostandfound.dao;

import java.util.List;

import com.lostandfound.beans.Data;
import com.lostandfound.beans.User;

/**
 * 数据库操作接口
 * 
 * @author lee
 *
 */
public interface LaFDaoImpl {
	// 插入新用户
	void insertUser(User user) throws Exception;

	// 通过帐号查询用户
	User findUserByPhone(String phone) throws Exception;

	// 通过姓名查询用户
	List<User> findUserByName(String name) throws Exception;

	// 修改用户信息
	void updataUser(User user) throws Exception;

	// 插入新事件
	void insertData(Data data) throws Exception;

	// 条件查询事件
	List<Data> selectAllData(String phone, String value) throws Exception;

	// 查询单个事件
	Data selectData(int id) throws Exception;

	// 修改事件信息
	void updataData(Data data) throws Exception;

	// 删除事件
	void deleteData(int id) throws Exception;

}
