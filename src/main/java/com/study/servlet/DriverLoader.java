package com.study.servlet;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class DriverLoader extends HttpServlet {

	@Override
	public void init() throws ServletException {		
		loadJDBCDriver();
		initConnectPool();
		
	}
	
	private void  loadJDBCDriver() {
		// 1. 드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OracleDriver 드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("fail to JDBC Driver ", e);
		}
	}
	
	
	private void  initConnectPool() {		
		try {
		
			ConnectionFactory connFactory = 
					 new DriverManagerConnectionFactory("jdbc:oracle:thin:@192.168.20.37:1521:xe"
							                            ,"java","oracle");
			
			PoolableConnectionFactory poolableConnFactory 
			    = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1 from dual");
			//풀 설정
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L *60L * 5L);
			poolConfig.setTestWhileIdle(true);
			
			poolConfig.setMinIdle(8);
			poolConfig.setMaxIdle(8);
			poolConfig.setMaxTotal(8);
			
			
			GenericObjectPool<PoolableConnection> connectionPool 
			   = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			//연결 URL 설정
			PoolingDriver driver =  (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("study",connectionPool);
			System.out.println("DBCP 로드");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
