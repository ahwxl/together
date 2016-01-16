package com.bplow.netconn.dbplay.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bplow.netconn.dbplay.dao.UserDao;

public class UserDaoImpl  implements UserDao{
	
	JdbcTemplate jdbcTemplateTest;

	@Override
	public void inserUser(String sql) {
		jdbcTemplateTest.execute(sql);
	}

	public void setJdbcTemplateTest(JdbcTemplate jdbcTemplateTest) {
		this.jdbcTemplateTest = jdbcTemplateTest;
	}
	
	

}
