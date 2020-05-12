package com.ibm.fsd.my.stock.bkg.security;

public enum JwtUserRoleEnum {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
	public String code;

	JwtUserRoleEnum(String code) {
		this.code = code;
	}
	
	public String getRole() {
		return this.code;
	}
}
