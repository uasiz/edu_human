package com.humanrest.database;

import java.sql.Timestamp;

public class StudentDto {

	private String sub_name, stu_name, stu_number, stu_password, stu_addr, stu_phone, stu_birthdayToString;
	private int available_rest, used_rest;
	private Timestamp stu_birthday;
	
	public StudentDto(String sub_name, String stu_name, String stu_number, String stu_birthdayToString, String stu_addr, String stu_phone,
			int available_rest, int used_rest) {
			super();
			this.sub_name = sub_name;
			this.stu_name = stu_name;
			this.stu_number = stu_number;
			this.setStu_birthdayToString(stu_birthdayToString);
			this.stu_addr = stu_addr;
			this.stu_phone = stu_phone;
			this.available_rest = available_rest;
			this.used_rest = used_rest;
		}
	
	public StudentDto(String sub_name, String stu_name, String stu_number, String stu_password, Timestamp stu_birthday, String stu_addr, String stu_phone,
		int available_rest, int used_rest) {
		super();
		this.sub_name = sub_name;
		this.stu_name = stu_name;
		this.stu_number = stu_number;
		this.stu_password = stu_password;
		this.stu_birthday = stu_birthday;
		this.stu_addr = stu_addr;
		this.stu_phone = stu_phone;
		this.available_rest = available_rest;
		this.used_rest = used_rest;
	}

	public StudentDto(String stu_number, String stu_password) {
		this.stu_number = stu_number;
		this.stu_password = stu_password;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_number() {
		return stu_number;
	}

	public void setStu_number(String stu_number) {
		this.stu_number = stu_number;
	}

	public String getStu_password() {
		return stu_password;
	}

	public void setStu_password(String stu_password) {
		this.stu_password = stu_password;
	}

	public String getStu_addr() {
		return stu_addr;
	}

	public void setStu_addr(String stu_addr) {
		this.stu_addr = stu_addr;
	}

	public String getStu_phone() {
		return stu_phone;
	}

	public void setStu_phone(String stu_phone) {
		this.stu_phone = stu_phone;
	}

	public int getAvailable_rest() {
		return available_rest;
	}

	public void setAvailable_rest(int available_rest) {
		this.available_rest = available_rest;
	}

	public int getUsed_rest() {
		return used_rest;
	}

	public void setUsed_rest(int used_rest) {
		this.used_rest = used_rest;
	}

	public Timestamp getStu_birthday() {
		return stu_birthday;
	}

	public void setStu_birthday(Timestamp stu_birthday) {
		this.stu_birthday = stu_birthday;
	}

	public String getStu_birthdayToString() {
		return stu_birthdayToString;
	}

	public void setStu_birthdayToString(String stu_birthdayToString) {
		this.stu_birthdayToString = stu_birthdayToString;
	}
	
	
	
}
