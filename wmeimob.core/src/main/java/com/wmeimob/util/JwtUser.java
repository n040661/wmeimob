package com.wmeimob.util;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements Serializable {

	private static final long serialVersionUID = -955272199747731688L;

	private String id;
	private String username;
	private String password;
	private Date lastPasswordResetDate;

	public JwtUser(String id, String username, String password, Date lastPasswordResetDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@JsonIgnore
	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	// 这个是自定义的，返回上次密码重置日期
	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

}