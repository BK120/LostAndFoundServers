package com.lostandfound.service;

import java.util.ArrayList;
import java.util.List;

import com.lostandfound.beans.Data;
import com.lostandfound.beans.User;
import com.lostandfound.dao.LaFDao;
import com.lostandfound.utils.JsonUtils;

/**
 * 数据库操作服务类
 * 
 * @author lee
 *
 */
public class LafService implements LaFServiceImpl {

	@Override
	public void sign(User user) throws Exception {
		// 用户注册
		LaFDao dao = new LaFDao();
		if (findUser(user.getPhone()).size() == 0) {
			dao.insertUser(user);
			throw new Exception("注册成功");
		} else {
			throw new Exception("用户已存在，请登录");
		}
	}

	@Override
	public List<User> findUser(String condition) throws Exception {
		// 通过电话或姓名查询用户
		LaFDao dao = new LaFDao();
		List<User> list = new ArrayList<User>();
		if (condition.startsWith("1")) {
			// 以1开头表示以电话查询
			User findUserByPhone = dao.findUserByPhone(condition);
			if (findUserByPhone.getName() != null) {
				list.add(findUserByPhone);
			}
		} else {
			// 以姓名查询
			list = dao.findUserByName(condition);
		}
		return list;
	}

	@Override
	public void login(String phone, String password) throws Exception {
		// 用户登录
		List<User> findUser = findUser(phone);
		if (findUser.size() == 0) {
			throw new Exception("用户不存在");
		} else {
			User user = findUser.get(0);
			String userJson = JsonUtils.userToJson(user).toString();
			if (user.getPassword().equals(password)) {
				throw new Exception(userJson);
			} else {
				throw new Exception("密码错误");
			}
		}
	}

	@Override
	public void updataUser(User user) throws Exception {
		// 修改用户信息
		LaFDao dao = new LaFDao();
		dao.updataUser(user);
		throw new Exception("保存修改");
	}

	@Override
	public void addEvent(Data data) throws Exception {
		// 添加事件
		LaFDao dao = new LaFDao();
		dao.insertData(data);
		throw new Exception("发布成功");
	}

	@Override
	public List<Data> selectDatas() throws Exception {
		// 查询所有事件
		return new LaFDao().selectAllData(null, null);
	}

	@Override
	public List<Data> selectDataNotFinish(String phone) throws Exception {
		// 查询当前用户所有事件
		return new LaFDao().selectAllData(phone,null);
	}

	@Override
	public List<Data> selectDataLost(String phone) throws Exception {
		// 查询当前用户丢失事件
		return new LaFDao().selectAllData(phone,"lost");
	}

	@Override
	public List<Data> selectDataFound(String phone) throws Exception {
		// 查询当前用户招领事件
		return new LaFDao().selectAllData(phone,"find");
	}

	@Override
	public void updataData(Data data) throws Exception {
		// 修改事件信息
		LaFDao dao = new LaFDao();
		dao.updataData(data);
		throw new Exception("保存修改");
	}

	@Override
	public List<Data> selectDataLost() throws Exception {
		// 查询所有丢失事件
		return new LaFDao().selectAllData(null,"lost");
	}

	@Override
	public List<Data> selectDataFound() throws Exception {
		// 查询所有招领事件
		return new LaFDao().selectAllData(null,"find");
	}

	@Override
	public Data selectData(int id) throws Exception {
		// 查询特定ID的事件
		LaFDao dao = new LaFDao();
		Data data = dao.selectData(id);
		if (data==null) {
			throw new Exception("该事件不存在");
		}
		return data;
	}

	@Override
	public void deleteData(int id) throws Exception {
		// 删除指定ID事件
		LaFDao dao = new LaFDao();
		dao.deleteData(id);
		throw new Exception("删除成功");
	}

}
