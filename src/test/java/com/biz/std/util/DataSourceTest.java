package com.biz.std.util;

import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataSourceTest {
	
	private ApplicationContext context = null;
	
	@Before
	public void setup(){
		context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/configs/spring/applicationContext.xml");	
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		context = null;
		System.out.println("tearDown");
	}
	
	@Test
	public void testDataSource(){
		DataSource dataSource = (DataSource)context.getBean("dataSource");
		Assert.assertNotNull(dataSource);
	}
	
	@Test
	public void testJdbcTemplate(){
		JdbcTemplate jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");
		Assert.assertNotNull(jdbcTemplate);
	}
}
