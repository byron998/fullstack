package com.ibm.fsd.my.stock.bkg.bean.result;

public enum TradeTypeEnum {
	// Buy
	TT_BUY("B"),
	// Sell
	TT_SELL("S"),
	// Default
	TT_DEFAULT("D");
	
	public String code;

	TradeTypeEnum(String code) {
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
