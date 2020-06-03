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

	public String getCode() {
		return code;
	}
	
}
