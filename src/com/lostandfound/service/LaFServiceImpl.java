package com.lostandfound.service;

import java.util.List;

import com.lostandfound.beans.Data;
import com.lostandfound.beans.User;

/**
 * 数据库操作服务接口
 * 
 * @author lee
 *
 */
public interface LaFServiceImpl {
	// 用户注册
	void sign(User user) throws Exception;

	// 查询用户
	List<User> findUser(String condition) throws Exception;

	// 用户登录
	void login(String phone, String password) throws Exception;

	// 修改用户信息
	void updataUser(User user) throws Exception;

	// 添加事件
	void addEvent(Data data) throws Exception;

	// 查询所有事件
	List<Data> selectDatas() throws Exception;

	// 查询所有丢失事件
	List<Data> selectDataLost() throws Exception;

	// 查询所有招领事件
	List<Data> selectDataFound() throws Exception;

	// 查询当前用户所有事件
	List<Data> selectDataNotFinish(String phone) throws Exception;

	// 查询当前所有丢失事件
	List<Data> selectDataLost(String phone) throws Exception;

	// 查询当前所有招领事件
	List<Data> selectDataFound(String phone) throws Exception;

	// 查询特定ID事件
	Data selectData(int id) throws Exception;

	// 修改事件信息
	void updataData(Data data) throws Exception;

	// 删除事件
	void deleteData(int id) throws Exception;
}
