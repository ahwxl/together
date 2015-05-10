package com.bplow.netconn.query.module;

public class ReqForm {
	
	private String id;
	private String c;
	private String ext;
	private String cnindx;
	
	
	public ReqForm() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getCnindx() {
		return cnindx;
	}
	public void setCnindx(String cnindx) {
		this.cnindx = cnindx;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	@Override
	public String toString() {
		return "ReqForm [id=" + id + ", ext=" + ext + ", cnindx=" + cnindx
				+ "]";
	}
	
	

}
