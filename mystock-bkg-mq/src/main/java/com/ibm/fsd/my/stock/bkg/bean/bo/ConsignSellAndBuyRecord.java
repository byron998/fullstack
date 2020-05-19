package com.ibm.fsd.my.stock.bkg.bean.bo;

import com.ibm.fsd.my.stock.bkg.domain.StockConsignSellRecord;
import com.ibm.fsd.my.stock.bkg.domain.StockConsignbuyRecord;

import lombok.Data;

@Data
public class ConsignSellAndBuyRecord {
	private StockConsignSellRecord sellRecord;
	private StockConsignbuyRecord buyRecord;
	public ConsignSellAndBuyRecord() {
		
	}
	public ConsignSellAndBuyRecord(StockConsignSellRecord sellRecord, StockConsignbuyRecord buyRecord) {
		this.buyRecord = buyRecord;
		this.sellRecord = sellRecord;
	}

}
