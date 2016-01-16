package com.alindus.iss.dto;

public class ChangePassword {
	private String email;
	private String oldPassword;
	private String password;
	private String rePassword;

	public ChangePassword() {
	}

	public ChangePassword(String email, String oldPassword, String password, String rePassword) {
		this.email = email;
		this.oldPassword = oldPassword;
		this.password = password;
		this.rePassword = rePassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

}
