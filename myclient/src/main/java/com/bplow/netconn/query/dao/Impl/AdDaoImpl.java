package com.bplow.netconn.query.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bplow.netconn.query.dao.AdDao;
import com.bplow.netconn.query.dao.entity.Ad;
import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class AdDaoImpl implements AdDao{

	@Autowired
	private SqlMapClient sqlMapClient;
	
	@Override
	public void insertAd(Ad ad) throws SQLException {
		sqlMapClient.insert("",ad);
	}

	@Override
	public Ad queryAd(int id) throws SQLException {
		Ad ad = (Ad)sqlMapClient.queryForObject("", id);
		return ad;
	}

	@Override
	public List queryAdList(Ad ad) throws SQLException {
		List list = sqlMapClient.queryForList("",ad);
		return list;
	}

}
