package com.ibm.fsd.my.stock.bkg.schedule;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.fsd.my.stock.bkg.bean.bo.ConsignSellAndBuyRecord;
import com.ibm.fsd.my.stock.bkg.domain.StockConsignSellRecord;
import com.ibm.fsd.my.stock.bkg.domain.StockConsignbuyRecord;
import com.ibm.fsd.my.stock.bkg.domain.StockPriceHistory;
import com.ibm.fsd.my.stock.bkg.service.StockConsignService;
import com.ibm.fsd.my.stock.bkg.service.StockTradeService;

public class MyStockThread implements Runnable{
	private final String tradeDate;
	private final Integer tradeCnt = 100;
	private final Double rate = 0.01;
	private boolean start = false;
	
	
	private final StockTradeService stockTradeService;
	
	private final StockConsignService stockConsignService;
	
	public MyStockThread(String input, StockTradeService stockTradeService, StockConsignService stockConsignService) {
		this.tradeDate = input;
		this.start = true;
		this.stockTradeService = stockTradeService;
		this.stockConsignService = stockConsignService;
	}
	public void stop() {
		this.start = false;
	}
	@Override
	public void run() {
		try {
			List<StockPriceHistory> tradeList = stockTradeService.getToDatePrices(tradeDate);
			if (tradeList == null || tradeList.size() == 0) {
				tradeList = stockTradeService.getLastDatePrices();
			}
			for (StockPriceHistory trade : tradeList) {
				if (this.tradeDate.equals(trade.getHistoryDate())) {
					
					List<StockConsignbuyRecord> buyList = stockTradeService.getTopNbuyByPrice(tradeCnt, trade.getStockId(), trade.getLastPrice());
					List<StockConsignSellRecord> selList = stockTradeService.getTopNSellByPrice(tradeCnt, trade.getStockId(), trade.getLastPrice());
					
					if (buyList.size() < tradeCnt || selList.size() < tradeCnt) {
						int minSize = this.doSomeTrade(buyList, selList, trade.getLastPrice(), trade.getStockId());
						if (buyList.size() == minSize) {
							Double newPrice = trade.getLastPrice() + rate;
							trade.setLastPrice(newPrice);
							if (trade.getTopPrice()< newPrice) {
								trade.setTopPrice(newPrice);
							}
							trade.setLastUpdateDt(new Date());
							stockTradeService.updateStockPriceHistory(trade);
						}
						else if (selList.size() == minSize) {
							Double newPrice = trade.getLastPrice() - rate;
							trade.setLastPrice(newPrice);
							if (trade.getLowPrice()> newPrice) {
								trade.setLowPrice(newPrice);
							}
							trade.setLastUpdateDt(new Date());
							stockTradeService.updateStockPriceHistory(trade);
						}
					}
				}
				else {
					StockPriceHistory todayPrice = new StockPriceHistory();
					todayPrice.setStockId(trade.getStockId());
					todayPrice.setHistoryDate(tradeDate);
					todayPrice.setLastPrice(trade.getClosedPrice());
					todayPrice.setLastUpdateDt(new Date());
					stockTradeService.insertStockPriceHistory(todayPrice);
					
					List<StockConsignbuyRecord> buyList = stockTradeService.getTopNbuyByPrice(tradeCnt, todayPrice.getStockId(), todayPrice.getLastPrice());
					List<StockConsignSellRecord> selList = stockTradeService.getTopNSellByPrice(tradeCnt, todayPrice.getStockId(), todayPrice.getLastPrice());
					

					if (buyList.size() < tradeCnt || selList.size() < tradeCnt) {
						int minSize = this.doSomeTrade(buyList, selList, trade.getLastPrice(), trade.getStockId());
						if (buyList.size() == minSize) {
							Double newPrice = trade.getLastPrice() + rate;
							trade.setLastPrice(newPrice);
							if (trade.getTopPrice()< newPrice) {
								trade.setTopPrice(newPrice);
							}
							trade.setLastUpdateDt(new Date());
							stockTradeService.updateStockPriceHistory(trade);
						}
						else if (selList.size() == minSize) {
							Double newPrice = trade.getLastPrice() - rate;
							trade.setLastPrice(newPrice);
							if (trade.getLowPrice()> newPrice) {
								trade.setLowPrice(newPrice);
							}
							trade.setLastUpdateDt(new Date());
							stockTradeService.updateStockPriceHistory(trade);
						}
					}
				}
				
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int doSomeTrade(List<StockConsignbuyRecord> buyList, List<StockConsignSellRecord> selList, Double price, Long stockId) throws Exception{
		int buyIdx = 0;
		int selIdx = 0;
		StockConsignSellRecord lastSell = null;
		StockConsignbuyRecord  lastBuy = null;
		while (buyIdx < buyList.size() && selIdx < selList.size()) {
			StockConsignSellRecord sel = lastSell != null ? selList.get(selIdx) : lastSell;
			StockConsignbuyRecord buy = lastBuy != null ? buyList.get(buyIdx) : lastBuy;
			Future<ConsignSellAndBuyRecord> resultFtu = stockTradeService.decreaseConsignRemainQt(sel, buy, price, stockId);
			ConsignSellAndBuyRecord result = resultFtu.get();
			if (this.start) {
				if (result.getBuyRecord() == null && result.getSellRecord() == null) {
					// not remains to next sell & buy
					buyIdx++;
					selIdx++;
					lastBuy = null;
					lastSell = null;
					continue;
				}
				else if (result.getBuyRecord() == null && result.getSellRecord() != null) {
					buyIdx++;
					lastBuy = null;
					lastSell = result.getSellRecord();
					continue;
				}
				else if (result.getBuyRecord() != null && result.getSellRecord() == null) {
					selIdx++;
					lastBuy = result.getBuyRecord();
					lastSell = null;
					continue;
				}
			}
			else {
				if(lastSell != null) {
					stockConsignService.updateConsignSell(lastSell);
				}
				if (lastBuy != null) {
					stockConsignService.updateConsignBuy(lastBuy);
				}
				return -1;
			}
		}
		return buyIdx > selIdx ? selIdx : buyIdx;
	}
}
