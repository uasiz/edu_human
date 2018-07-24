package com.humanMind.client;

import java.io.Serializable;

public class MyLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5157720629098977049L;

	private String id, pw;
	
	public MyLogin() {
		
	}
	
	public MyLogin(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
