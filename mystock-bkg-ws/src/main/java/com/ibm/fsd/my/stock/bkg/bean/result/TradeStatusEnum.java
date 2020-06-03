package com.ibm.fsd.my.stock.bkg.bean.result;

public enum TradeStatusEnum {
	// CANCEL
	TS_CANCEL("00"),
	// CONSIGN
	TS_CONSIGN("01"),
	// TRADE
	TT_TRADE("02");
	
	public String code;

	TradeStatusEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
