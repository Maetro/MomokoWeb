package com.momoko.es.commons.domain;

import com.momoko.es.commons.validation.Password;

import javax.validation.constraints.NotBlank;


public class ResetPasswordForm {
	
	@NotBlank
	private String code;
	
	@Password
	private String newPassword;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
