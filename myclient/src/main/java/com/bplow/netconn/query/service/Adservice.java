package com.bplow.netconn.query.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.module.ReqForm;

public interface Adservice {
	
	public void getAdById();
	
	/**
	 * 获取basejs
	 */
	public String  obtionBaseScript(ReqForm reqForm,InputStream  in ,OutputStream out);
	/**
	 * 获取执行方法，指定要加载客户包装后客户js
	 * @return
	 */
	public String executeMethod(ReqForm req,OutputStream os);
	/**
	 * 请求包装的js
	 * @return
	 */
	public String requestCustomerJs();
	
	public String addAd(Ad ad) throws SQLException;
	
	public List queryAdList(Ad ad) throws SQLException;
	
	public void updateAd(Ad ad) throws SQLException;

}
