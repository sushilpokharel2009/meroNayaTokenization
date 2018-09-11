package com.meroTokenization.model;

import java.io.Serializable;

public class MeroAcntVO implements Serializable{
	
	private String meroAcntNo;
	private String routingNo;
	private String randomId;
	private String tokenAccountNo;
	public String getMeroAcntNo() {
		return meroAcntNo;
	}
	public void setMeroAcntNo(String meroAcntNo) {
		this.meroAcntNo = meroAcntNo;
	}
	public String getRoutingNo() {
		return routingNo;
	}
	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}
	public String getRandomId() {
		return randomId;
	}
	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}
	public String getTokenAccountNo() {
		return tokenAccountNo;
	}
	public void setTokenAccountNo(String tokenAccountNo) {
		this.tokenAccountNo = tokenAccountNo;
	}
	@Override
	public String toString() {
		return "MeroAcntVO [meroAcntNo=" + meroAcntNo + ", routingNo=" + routingNo + ", randomId=" + randomId
				+ ", tokenAccountNo=" + tokenAccountNo + "]";
	}
	
	
	
}
