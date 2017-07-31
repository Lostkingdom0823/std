package com.biz.std.util;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import com.mysql.jdbc.Statement;
import java.sql.Connection;

public class JDBCUtil {
	
	public static Connection getConnetion() throws Exception{
		
		InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		
		String url = properties.getProperty("jdbc.url");
		String user = properties.getProperty("jdbc.user");
		String password = properties.getProperty("jdbc.password");
		String driverClass = properties.getProperty("jdbc.driverClass");
		
		Class.forName(driverClass);
		Connection connection = DriverManager.getConnection(url,user,password);
	
		return connection;	
	}
	
	/**
	 * 关闭相关资源
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void release(ResultSet resultSet,Statement statement,Connection connection){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null){
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
