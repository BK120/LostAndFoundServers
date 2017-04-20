package com.lostandfound.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * JDBC数据库连接工厂类
 * @author lee
 *
 */
public class ConnectionFactory {
	private static final String DRIVER;
	private static final String URL;
	private static final String USER;
	private static final String PASSWORD;
	
	static{
		
		DRIVER = "oracle.jdbc.driver.OracleDriver";
		URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		USER = "oracle";
		PASSWORD = "oracle";
	}
	
	public static Connection getConnection() throws Exception{
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("类加载失败");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库连接失败");
		}
		return conn;
	}

}
