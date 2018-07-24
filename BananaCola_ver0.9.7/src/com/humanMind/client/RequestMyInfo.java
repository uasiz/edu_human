package com.humanMind.client;

import java.io.Serializable;

public class RequestMyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7900579923584914295L;

	private String id;
	
	public RequestMyInfo() {
		
	}
	
	public RequestMyInfo(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
