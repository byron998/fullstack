package com.ibm.fsd.my.stock.bkg.bean.result;

public enum VarcharBoolean {
	// True
	BOOL_TRUE("y"),
	// False
	BOOL_FALSE("n"),
	// Null
	BOOL_NULL("");
	
	public String code;

	VarcharBoolean(String code) {
		this.code = code;
	}
	
	public Boolean getBoolean() {
		if (this.code.equals("y")) {
			return true;
		} 
		else if (this.code.equals("n")) {
			return false;
		}
		else {
			return null;
		}
	}
}
