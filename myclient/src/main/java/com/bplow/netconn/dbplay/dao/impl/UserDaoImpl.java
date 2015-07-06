package com.bplow.netconn.dbplay.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bplow.netconn.dbplay.dao.UserDao;

//@Service("userDaoTest")
public class UserDaoImpl  implements UserDao{
	
	//@Autowired
	//@Qualifier("jdbcTemplateTest")
	JdbcTemplate jdbcTemplateTest;

	@Override
	public void inserUser(String sql) {
		jdbcTemplateTest.execute(sql);
	}

}
