package com.lostandfound.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lostandfound.beans.Data;
import com.lostandfound.beans.User;
import com.lostandfound.jdbc.HandlerResult;
import com.lostandfound.jdbc.JDBCTemp;
import com.lostandfound.jdbc.PrepareSetter;

/**
 * 数据库操作类
 * 
 * @author lee
 *
 */
public class LaFDao implements LaFDaoImpl {

	@Override
	public void insertUser(User user) throws Exception {
		// 插入新用户
		JDBCTemp jdbcTemp = new JDBCTemp();
		String sql = "insert into laf_user(phone,password,name,sex,address) values(?,?,?,?,?)";
		jdbcTemp.execute(sql, new PrepareSetter() {

			@Override
			public void setter(PreparedStatement preparedStatement)
					throws Exception {
				// 设置参数
				preparedStatement.setString(1, user.getPhone());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getName());
				preparedStatement.setString(4, user.getSex());
				preparedStatement.setString(5, user.getAddress());
			}
		});
	}

	@Override
	public User findUserByPhone(String phone) throws Exception {
		// 通过电话查询用户
		JDBCTemp jdbcTemp = new JDBCTemp();
		String sql = "select phone,password,name,sex,address "
				+ "from laf_user " + "where phone=?";
		User user = new User();
		jdbcTemp.execute(sql, new PrepareSetter() {

			@Override
			public void setter(PreparedStatement preparedStatement)
					throws Exception {
				// 设置参数
				preparedStatement.setString(1, phone);
			}
		}, new HandlerResult() {

			@Override
			public void handle(ResultSet resultSet) throws Exception {
				// 处理结果
				if (resultSet.next()) {
					user.setPhone(resultSet.getString(1));
					user.setPassword(resultSet.getString(2));
					user.setName(resultSet.getString(3));
					user.setSex(resultSet.getString(4));
					user.setAddress(resultSet.getString(5));
				}
			}
		});
		return user;
	}

	@Override
	public List<User> findUserByName(String name) throws Exception {
		// 通过姓名查询用户
		JDBCTemp jdbcTemp = new JDBCTemp();
		List<User> list = new ArrayList<User>();
		String sql = "select phone,password,name,sex,address "
				+ "from laf_user " + "where like ?";
		jdbcTemp.execute(sql, new PrepareSetter() {

			@Override
			public void setter(PreparedStatement preparedStatement)
					throws Exception {
				// 设置参数
				preparedStatement.setString(1, "%" + name + "%");
			}
		}, new HandlerResult() {

			@Override
			public void handle(ResultSet resultSet) throws Exception {
				// 处理结果
				while (resultSet.next()) {
					list.add(new User(resultSet.getString(1), resultSet
							.getString(2), resultSet.getString(3), resultSet
							.getString(4), resultSet.getString(5)));
				}
			}
		});
		return list;
	}

	@Override
	public void updataUser(User user) throws Exception {
		// 修改用户信息
		JDBCTemp jdbcTemp = new JDBCTemp();
		String sql = "update laf_user "
				+ "set password=?,name=?,sex=?,address=? " + "where phone=?";
		jdbcTemp.execute(sql, new PrepareSetter() {

			@Override
			public void setter(PreparedStatement preparedStatement)
					throws Exception {
				// 设置参数
				preparedStatement.setString(1, user.getPassword());
				preparedStatement.setString(2, user.getName());
				preparedStatement.setString(3, user.getSex());
				preparedStatement.setString(4, user.getAddress());
				preparedStatement.setString(5, user.getPhone());
			}
		});
	}

	@Override
	public void insertData(Data data) throws Exception {
		// 插入新事件
		JDBCTemp jdbcTemp = new JDBCTemp();
		String idSql = "select laf_data_seq.nextval from dual";
		jdbcTemp.execute(idSql, new HandlerResult() {

			@Override
			public void handle(ResultSet resultSet) throws Exception {
				// 序列获取自增长ID
				if (resultSet.next()) {
					data.setId(resultSet.getInt(1));
				}
			}
		});
		String sql = "insert into laf_data(id,type,name,detail,remark,place,incident_date1,incident_time1,incident_date2,incident_time2,publish_date,publish_time,isfinish,pic_name,user_phone) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemp.execute(sql, new PrepareSetter() {

			@Override
			public void setter(PreparedStatement preparedStatement)
					throws Exception {
				// TODO Auto-generated method stub
				preparedStatement.setInt(1, data.getId());
				preparedStatement.setString(2, data.getType());
				preparedStatement.setString(3, data.getName());
				preparedStatement.setString(4, data.getDetail());
				preparedStatement.setString(5, data.getRemark());
				preparedStatement.setString(6, data.getPlace());
				preparedStatement.setDate(7, data.getIncidentDate1());
				preparedStatement.setString(8, data.getIncidentTime1());
				preparedStatement.setDate(9, data.getIncidentDate2());
				preparedStatement.setString(10, data.getIncidentTime2());
				preparedStatement.setDate(11, data.getPublishDate());
				preparedStatement.setString(12, data.getPublishTime());
				preparedStatement.setString(13, data.getIsFinish());
				preparedStatement.setString(14, data.getPicName());
				preparedStatement.setString(15, data.getUserPhone());
			}
		});
	}

	@Override
	public List<Data> selectAllData(String phone, String value)
			throws Exception {
		// 条件查询事件
		JDBCTemp jdbcTemp = new JDBCTemp();
		List<Data> list = new ArrayList<Data>();
		String sql = "";
		String sql1 = "select id,type,name,detail,remark,place,incident_date1,incident_time1,incident_date2,incident_time2,publish_date,publish_time,isfinish,pic_name,user_phone from laf_data ";
		String sql2 = "";
		String sql3 = " order by id desc";
		PrepareSetter setter = new PrepareSetter() {

			@Override
			public void setter(PreparedStatement preparedStatement)
					throws Exception {
				// TODO Auto-generated method stub
				if (phone == null && value != null) {
					preparedStatement.setString(1, value);
				} else if (phone != null && value == null) {
					preparedStatement.setString(1, phone);
				} else {
					preparedStatement.setString(1, phone);
					preparedStatement.setString(2, value);
				}
			}
		};
		HandlerResult result = new HandlerResult() {

			@Override
			public void handle(ResultSet resultSet) throws Exception {
				// TODO Auto-generated method stub
				while (resultSet.next()) {
					Data data = new Data();
					data.setId(resultSet.getInt(1));
					data.setType(resultSet.getString(2));
					data.setName(resultSet.getString(3));
					data.setDetail(resultSet.getString(4));
					data.setRemark(resultSet.getString(5));
					data.setPlace(resultSet.getString(6));
					data.setIncidentDate1(resultSet.getDate(7));
					data.setIncidentTime1(resultSet.getString(8));
					data.setIncidentDate2(resultSet.getDate(9));
					data.setIncidentTime2(resultSet.getString(10));
					data.setPublishDate(resultSet.getDate(11));
					data.setPublishTime(resultSet.getString(12));
					data.setIsFinish(resultSet.getString(13));
					data.setPicName(resultSet.getString(14));
					data.setUserPhone(resultSet.getString(15));
					list.add(data);
				}
			}
		};
		if (phone == null && value == null) {
			setter = null;
		} else if (phone == null && value != null) {
			sql2 = "where type=?";
		} else if (phone != null && value == null) {
			sql2 = "where user_phone=?";
		} else {
			sql2 = "where user_phone=? and type=?";
		}
		sql = sql1 + sql2 + sql3;
		jdbcTemp.execute(sql, setter, result);
		return list;
	}

	@Override
	public void updataData(Data data) throws Exception {
		// 修改事件信息
		JDBCTemp jdbcTemp = new JDBCTemp();
		String sql = "update laf_data set type=?,name=?,detail=?,remark=?,place=?,incident_date1=?,incident_date2=?,incident_time1=?,incident_time2=?,isfinish=?";
		jdbcTemp.execute(sql, new PrepareSetter() {

			@Override
			public void setter(PreparedStatement preparedStatement)
					throws Exception {
				// TODO Auto-generated method stub
				preparedStatement.setString(1, data.getType());
				preparedStatement.setString(2, data.getName());
				preparedStatement.setString(3, data.getDetail());
				preparedStatement.setString(4, data.getRemark());
				preparedStatement.setString(5, data.getPlace());
				preparedStatement.setDate(6, data.getIncidentDate1());
				preparedStatement.setString(7, data.getIncidentTime1());
				preparedStatement.setDate(8, data.getIncidentDate2());
				preparedStatement.setString(9, data.getIncidentTime2());
				preparedStatement.setString(10, data.getIsFinish());
			}
		});
	}

	@Override
	public Data selectData(int id) throws Exception {
		// 通过id查询单个事件
		JDBCTemp jdbcTemp = new JDBCTemp();
		Data data = new Data();
		String sql = "select id,type,name,detail,remark,place,incident_date1,incident_time1,incident_date2,incident_time2,publish_date,publish_time,isfinish,pic_name,user_phone from laf_data "
				+ "where id=?";
		jdbcTemp.execute(sql, new PrepareSetter() {

			@Override
			public void setter(PreparedStatement preparedStatement)
					throws Exception {
				// TODO Auto-generated method stub
				preparedStatement.setInt(1, id);
			}
		}, new HandlerResult() {

			@Override
			public void handle(ResultSet resultSet) throws Exception {
				// TODO Auto-generated method stub
				if (resultSet.next()) {
					data.setId(resultSet.getInt(1));
					data.setType(resultSet.getString(2));
					data.setName(resultSet.getString(3));
					data.setDetail(resultSet.getString(4));
					data.setRemark(resultSet.getString(5));
					data.setPlace(resultSet.getString(6));
					data.setIncidentDate1(resultSet.getDate(7));
					data.setIncidentTime1(resultSet.getString(8));
					data.setIncidentDate2(resultSet.getDate(9));
					data.setIncidentTime2(resultSet.getString(10));
					data.setPublishDate(resultSet.getDate(11));
					data.setPublishTime(resultSet.getString(12));
					data.setIsFinish(resultSet.getString(13));
					data.setPicName(resultSet.getString(14));
					data.setUserPhone(resultSet.getString(15));
				}
			}
		});
		return data;
	}

	@Override
	public void deleteData(int id) throws Exception {
		// 删除事件
		JDBCTemp jdbcTemp = new JDBCTemp();
		String sql = "delete from laf_data where id=?";
		jdbcTemp.execute(sql, new PrepareSetter() {
			
			@Override
			public void setter(PreparedStatement preparedStatement) throws Exception {
				// TODO Auto-generated method stub
				preparedStatement.setInt(1, id);
			}
		});
	}

}
