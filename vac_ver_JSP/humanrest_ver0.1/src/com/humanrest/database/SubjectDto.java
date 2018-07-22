package com.humanrest.database;

import java.sql.Timestamp;

public class SubjectDto {

	private String subName, subStartToString, subEndToString;
	private Timestamp subStart, subEnd;
	
	public SubjectDto(String subName) {
		this.subName = subName;
	}
	
	public SubjectDto(String subName, Timestamp subStart, Timestamp subEnd) {
		super();
		this.subName = subName;
		this.subStart = subStart;
		this.subEnd = subEnd;
	}
	public SubjectDto(String subName, String subStart, String subEnd) {
		super();
		this.subName = subName;
		this.setSubStartToString(subStart);
		this.setSubEndToString(subEnd);
	}
	
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Timestamp getSubStart() {
		return subStart;
	}
	public void setSubStart(Timestamp subStart) {
		this.subStart = subStart;
	}
	public Timestamp getSubEnd() {
		return subEnd;
	}
	public void setSubEnd(Timestamp subEnd) {
		this.subEnd = subEnd;
	}

	public String getSubStartToString() {
		return subStartToString;
	}

	public void setSubStartToString(String subStartToString) {
		this.subStartToString = subStartToString;
	}

	public String getSubEndToString() {
		return subEndToString;
	}

	public void setSubEndToString(String subEndToString) {
		this.subEndToString = subEndToString;
	}
	
	
}
