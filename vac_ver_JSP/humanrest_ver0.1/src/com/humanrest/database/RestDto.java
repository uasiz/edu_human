package com.humanrest.database;

import java.sql.Date;

public class RestDto {

	private int rest_index, rest_num;
	private String sub_name, stu_number, stu_name, rest_reason, stu_check, manage_check, verified;
	private Date rest_request_date, rest_date;
	
	// rest_index, sub_name, stu_number, stu_name, rest_reqeust_date, rest_date, rest_num, rest_reason, verified
	
	public RestDto(int rest_index, String sub_name, String stu_number, String stu_name,
			Date rest_request_date, Date rest_date,  int rest_num, String rest_reason, String verified, String stu_check) {
		super();
		this.rest_index = rest_index;
		this.rest_num = rest_num;
		this.sub_name = sub_name;
		this.stu_number = stu_number;
		this.stu_name = stu_name;
		this.rest_reason = rest_reason;
		this.verified = verified;
		this.rest_request_date = rest_request_date;
		this.rest_date = rest_date;
		this.stu_check = stu_check;
	}
	public int getRest_index() {
		return rest_index;
	}
	public void setRest_index(int rest_index) {
		this.rest_index = rest_index;
	}
	public int getRest_num() {
		return rest_num;
	}
	public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public String getStu_number() {
		return stu_number;
	}
	public void setStu_number(String stu_number) {
		this.stu_number = stu_number;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getRest_reason() {
		return rest_reason;
	}
	public void setRest_reason(String rest_reason) {
		this.rest_reason = rest_reason;
	}
	public String getStu_check() {
		return stu_check;
	}
	public void setStu_check(String stu_check) {
		this.stu_check = stu_check;
	}
	public String getManage_check() {
		return manage_check;
	}
	public void setManage_check(String manage_check) {
		this.manage_check = manage_check;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public Date getRest_request_date() {
		return rest_request_date;
	}
	public void setRest_request_date(Date rest_request_date) {
		this.rest_request_date = rest_request_date;
	}
	public Date getRest_date() {
		return rest_date;
	}
	public void setRest_date(Date rest_date) {
		this.rest_date = rest_date;
	}
	
}
