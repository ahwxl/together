package com.bplow.netconn.query.dao.entity;

public class Ad {
	
	private int id;
	private int adId;
	private String adName;
	private String adDesc;
	private int reqNum;
	private String adContent;
	private int adResNum;
	private String customerName;
	private String adAddrName;
	private int orderBy;
	
	
	public Ad() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAdId() {
		return adId;
	}


	public void setAdId(int adId) {
		this.adId = adId;
	}


	public String getAdName() {
		return adName;
	}


	public void setAdName(String adName) {
		this.adName = adName;
	}


	public String getAdDesc() {
		return adDesc;
	}


	public void setAdDesc(String adDesc) {
		this.adDesc = adDesc;
	}


	public int getReqNum() {
		return reqNum;
	}


	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}


	public String getAdContent() {
		return adContent;
	}


	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}


	public int getAdResNum() {
		return adResNum;
	}


	public void setAdResNum(int adResNum) {
		this.adResNum = adResNum;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getAdAddrName() {
		return adAddrName;
	}


	public void setAdAddrName(String adAddrName) {
		this.adAddrName = adAddrName;
	}


	public int getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	
	

}
