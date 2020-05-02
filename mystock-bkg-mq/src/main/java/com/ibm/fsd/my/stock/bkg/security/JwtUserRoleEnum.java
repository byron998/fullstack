package com.ibm.fsd.my.stock.bkg.security;

public enum JwtUserRoleEnum {
	USER("USER"), ADMIN("ADMIN");
	public String code;

	JwtUserRoleEnum(String code) {
		this.code = code;
	}
	
	public String getRole() {
		return this.code;
	}
}
