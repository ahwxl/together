package com.bplow.netconn.query.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.bplow.netconn.base.tmpl.VelocityHelper;
import com.bplow.netconn.query.dao.AdDao;
import com.bplow.netconn.query.dao.entity.Ad;
import com.bplow.netconn.query.module.ReqForm;
import com.bplow.netconn.query.service.Adservice;

@Service
public class AdServiceImpl implements Adservice{

	private static Logger logger = LoggerFactory.getLogger(AdServiceImpl.class);
	@Autowired
	private AdDao adDao;
	
	//@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private VelocityHelper velocityHelper;
	
	@Override
	public void getAdById() {
		
	}

	@Override
	public String obtionBaseScript(ReqForm reqForm,InputStream  in ,OutputStream out) {
		logger.info("获取基础JS:{}",reqForm);
		Map map = new HashMap();
		map.put("ext", reqForm.getExt());
		String str = null;
		try {
			str = velocityHelper.renderTemplate(map, in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public String executeMethod(ReqForm reqForm,OutputStream os) {
		String exeNum = reqForm.getC();
		String cnidx  = reqForm.getCnidx();
		String ext    = reqForm.getExt();
		String adId   = null;
		String adName = "";
		String property ="";
		String [] adArray = ext.split("|");
		
		if(StringUtils.isNotBlank(cnidx)){
			int cnidxnum = Integer.parseInt(cnidx);
			if(cnidxnum < adArray.length){
				adId = adArray[cnidxnum];
			}
			
		}
		
		if("1".equals(cnidx)){
			adName = "MZADX";
			property = "{'l':'9556'}";//  7577
		}else{
			adName = "YOUDAO";
			property = "{'slotid':'1387'}";
		}
		
		String str = "(function (win, doc) {"
				+ exeNum + "(0,'" + adName
				+ "',"+property+")})(window, document);";
		logger.info("executeJS:{}",str);
		return str;
	}

	@Override
	public String requestCustomerJs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addAd(Ad ad) throws SQLException {
		adDao.insertAd(ad);
		return null;
	}

	@Override
	public List queryAdList(Ad ad) throws SQLException {
		return null;
	}

	@Override
	public void updateAd(Ad ad) throws SQLException {
		
	}

}
