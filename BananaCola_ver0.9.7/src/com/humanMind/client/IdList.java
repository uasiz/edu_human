package com.humanMind.client;

import java.io.Serializable;

public class IdList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5217065316285579405L;
	
	private String id;

	public IdList() {
		
	}
	
	public IdList(String id) {
		super();
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
