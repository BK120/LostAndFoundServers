package com.lostandfound.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 关闭资源工具
 * 
 * @author lee
 *
 */
public class DBCloseUtils {
	public static void close(Statement statement) {
		close(statement, null);
	}

	public static void close(Statement statement, ResultSet resultSet) {
		try {
			if (statement != null)
				statement.close();
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("资源关闭失败");
		}
	}

}
