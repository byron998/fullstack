package com.ibm.fsd.my.stock.bkg.schedule;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ibm.fsd.my.stock.bkg.domain.StockPriceHistory;
import com.ibm.fsd.my.stock.bkg.service.StockTradeService;

@Component
public class MyStockTradeTask {
//	@Autowired
//	Queue helloQueue;
	
	@Autowired
	StockTradeService stockTradeService;
	
	public boolean selfTradeFlg;
	public String tradingDate;
	public List<StockPriceHistory> sotckPrices;
	public MyStockTradeTask() {
		this.selfTradeFlg = false;
	}
	
	@Scheduled(cron = "0 56 23 ? * MON-FRI")
	//@Scheduled(cron = "0 30 9 ? * MON-FRI") //every working day AM 09:30
	public void MyStockTradeStartTask(){
		System.out.println("The trade is start at " + new Timestamp(System.currentTimeMillis()).toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		this.tradingDate = sdf.format(new Date());
		this.sotckPrices = stockTradeService.getLastDatePrices();
		// first trade
		// open market
		// use bean flg set trade is start.
		this.selfTradeFlg = true;
	}
	
	@Scheduled(cron = "0 58 23 ? * MON-FRI")
	//@Scheduled(cron = "0 0 15 ? * MON-FRI") //every working day PM 15:00
	public void MyStockTradeCloseTask(){
		//use bean flg set trade is close.
		this.selfTradeFlg = false;
		System.out.println("The trade is close at " + new Timestamp(System.currentTimeMillis()).toString());
	}
	
	@Scheduled(cron = "*/15 * 9-15 ? * MON-FRI") //every working day AM 09:30 ~ PM 15:00 every 15 seconds
	public void MyStockOnTradingTask(){
		if (this.selfTradeFlg) {
			System.out.println("The market is trading at " + new Timestamp(System.currentTimeMillis()).toString());
			//stockTradeService.getTopNbuyByPrice(top, price);
			//stockTradeService.getTopNSellByPrice(top, price);
		}
	}
}
