package com.lostandfound.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBD模版类
 * 
 * @author lee
 *
 */
public class JDBCTemp {
	private Connection conn;

	public JDBCTemp() throws Exception {
		this(null);
	}

	public JDBCTemp(Connection conn) throws Exception {
		if (conn == null) {
			this.conn = ConnectionFactory.getConnection();
		} else {
			this.conn = conn;
		}
	}

	/**
	 * 无输入无输出
	 * 
	 * @param sql
	 */
	public void execute(String sql) {
		Statement statement = null;
		try {
			statement = conn.createStatement();
			System.out.println("Oracle反馈：" + statement.execute(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCloseUtils.close(statement);
		}
	}

	/**
	 * 有输入无输出
	 * 
	 * @param sql
	 * @param prepareSetter
	 */
	public void execute(String sql, PrepareSetter prepareSetter) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if (prepareSetter != null) {
				prepareSetter.setter(preparedStatement);
				System.out.println("Oracle反馈："
						+ preparedStatement.executeUpdate());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCloseUtils.close(preparedStatement);
		}
	}

	/**
	 * 无输入有输出
	 * 
	 * @param sql
	 * @param handlerResult
	 */
	public void execute(String sql, HandlerResult handlerResult) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			if (handlerResult != null) {
				handlerResult.handle(resultSet);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCloseUtils.close(statement, resultSet);
		}
	}

	/**
	 * 有输入有输出
	 * 
	 * @param sql
	 * @param preparedStatement
	 * @param handlerResult
	 */
	public void execute(String sql, PrepareSetter prepareSetter,
			HandlerResult handlerResult) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if (prepareSetter != null) {
				prepareSetter.setter(preparedStatement);
			}
			resultSet = preparedStatement.executeQuery();
			if (handlerResult != null) {
				handlerResult.handle(resultSet);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCloseUtils.close(preparedStatement, resultSet);
		}
	}

}
