package com.bplow.netconn.dbplay.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bplow.netconn.dbplay.dao.UserDao;


@ContextConfiguration(locations = { "/applicationContext.xml","/zdal_ds.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserDaoTest {
	
	@Autowired
	@Qualifier("userDaoTest")
	private UserDao userDao;
	
	@Test
	public void inserUserTest(){
		String sql = " insert into user(id,name) values(2,'张三')";
		userDao.inserUser(sql);
	}

}
