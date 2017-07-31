package com.biz.std.util;

import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;

public class JDBCUtilTest {
	
	@Test
	public void testGetConnection() throws Exception{
		Connection connection = JDBCUtil.getConnetion();
		Assert.assertNotNull(connection);
	}
}
