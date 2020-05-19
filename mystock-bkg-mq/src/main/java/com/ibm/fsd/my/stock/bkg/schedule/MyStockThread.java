package com.ibm.fsd.my.stock.bkg.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.fsd.my.stock.bkg.domain.StockPriceHistory;
import com.ibm.fsd.my.stock.bkg.service.StockTradeService;

public class MyStockThread extends Thread {
	private String tradeDate;
	@Autowired
	private StockTradeService stockTradeService;
	public MyStockThread(String input) {
		this.tradeDate = input;
	}
	
	public void run() {
		try {
			List<StockPriceHistory> tradeList = stockTradeService.getToDatePrices(tradeDate);
			if (tradeList == null || tradeList.size() == 0) {
				tradeList = stockTradeService.getLastDatePrices();
			}
			for (StockPriceHistory trade : tradeList) {
				if (this.tradeDate.equals(trade.getHistoryDate())) {
//					trade.getStockId() trade.getLastPrice()
				}
//				stockTradeService.decreaseConsignRemainQt(sellRecord, buyRecord, price, stockId);
			}
		}
		catch (Exception ex) {
			
		}
	}
}
