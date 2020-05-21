package com.ibm.fsd.my.stock.bkg.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fsd.my.stock.bkg.bean.bo.ConsignSellAndBuyRecord;
import com.ibm.fsd.my.stock.bkg.bean.result.TradeTypeEnum;
import com.ibm.fsd.my.stock.bkg.domain.StockConsignSellRecord;
import com.ibm.fsd.my.stock.bkg.domain.StockConsignbuyRecord;
import com.ibm.fsd.my.stock.bkg.domain.StockPriceHistory;
import com.ibm.fsd.my.stock.bkg.domain.StockTradeRecord;
import com.ibm.fsd.my.stock.bkg.domain.UserHoldStock;
import com.ibm.fsd.my.stock.bkg.mapper.StockConsignSellRecordCustMapper;
import com.ibm.fsd.my.stock.bkg.mapper.StockConsignbuyRecordCustMapper;
import com.ibm.fsd.my.stock.bkg.mapper.StockPriceHistoryCustMapper;
import com.ibm.fsd.my.stock.bkg.mapper.StockTradeRecordMapper;
import com.ibm.fsd.my.stock.bkg.mapper.UserHoldStockMapper;
@Service
public class StockTradeService {
	@Autowired
	private StockConsignbuyRecordCustMapper stockConsignbuyRecordMapper;
	@Autowired
	private StockConsignSellRecordCustMapper stockConsignSellRecordMapper;
	@Autowired
	private StockTradeRecordMapper stockTradeRecordMapper;
	@Autowired
	private UserHoldStockMapper userHoldStockMapper;
	@Autowired
	private StockPriceHistoryCustMapper stockPriceHistoryCustMapper;
	
	public List<StockConsignSellRecord> getTopNSellByPrice(Integer top, Long stockid, Double price) {
		return stockConsignSellRecordMapper.getTopNWaitingByPrice("01", stockid, price, top);
	}
	
	public List<StockConsignbuyRecord> getTopNbuyByPrice(Integer top, Long stockid, Double price) {
		return stockConsignbuyRecordMapper.getTopNWaitingByPrice("01", stockid, price, top);
	}
	
	public List<StockPriceHistory> getLastDatePrices() {
		return stockPriceHistoryCustMapper.getLastDatePrices();
	}
	public List<StockPriceHistory> getToDatePrices(String today) {
		return stockPriceHistoryCustMapper.getToDatePrices(today);
	}
	
	public Integer insertStockPriceHistory(StockPriceHistory record) {
		return stockPriceHistoryCustMapper.insertSelective(record);
	}
	
	public Integer updateStockPriceHistory(StockPriceHistory record) {
		return stockPriceHistoryCustMapper.updateByPrimaryKeySelective(record);
	}
	
	@Async //异步处理
	@Transactional(rollbackFor = {Exception.class})
	public Future<ConsignSellAndBuyRecord> decreaseConsignRemainQt(StockConsignSellRecord sellRecord, StockConsignbuyRecord buyRecord, 
			 Double price, Long stockId) throws Exception{
		ConsignSellAndBuyRecord returnObj = new ConsignSellAndBuyRecord();
		Long RemainQt = 0l;
		if (sellRecord.getRemainsQt() <= buyRecord.getRemainsQt()) {
			RemainQt = sellRecord.getRemainsQt();
		}
		else if (sellRecord.getRemainsQt() >= buyRecord.getRemainsQt()) {
			RemainQt = buyRecord.getRemainsQt();
		}
		if (RemainQt == 0l) {
			throw new Exception("buy or sell has zero remains.");
		}
		if (sellRecord.getRemainsQt() == RemainQt) {
			// decrease the consign remains
			int sellNum = stockConsignSellRecordMapper.decreaseConsignSellRemains(sellRecord.getId(), RemainQt, true, sellRecord.getLastUpdateDt());
			if (sellNum==0) {
				throw new Exception("buy or sell not update consign.");
			}
			returnObj.setSellRecord(null);
		}
		else {
			sellRecord.setRemainsQt(sellRecord.getRemainsQt() - RemainQt);
			returnObj.setSellRecord(sellRecord);
		}
		
		if (buyRecord.getRemainsQt() == RemainQt) {
			int buyNum = stockConsignbuyRecordMapper.decreaseConsignbuyRemains(buyRecord.getId(), RemainQt, true, buyRecord.getLastUpdateDt());
			if (buyNum==0) {
				throw new Exception("buy or sell not update consign.");
			}
			returnObj.setBuyRecord(null);
		}
		else {
			buyRecord.setRemainsQt(buyRecord.getRemainsQt() - RemainQt);
			returnObj.setBuyRecord(buyRecord);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// insert trade record
		StockTradeRecord tradeRecord = new StockTradeRecord();
		tradeRecord.setBuyRecordId(buyRecord.getId());
		tradeRecord.setSellRecordId(sellRecord.getId());
		tradeRecord.setStockId(stockId);
		tradeRecord.setTradeQt(RemainQt);
		tradeRecord.setTradePrice(price);
		tradeRecord.setTradeDate(sdf.format(new Date()));
		stockTradeRecordMapper.insertSelective(tradeRecord);
		
		// insert user holding stock record
		UserHoldStock holdsell = new UserHoldStock();
		holdsell.setStockId(stockId);
		holdsell.setUserId(sellRecord.getUserId());
		holdsell.setHoldQt(RemainQt);
		holdsell.setHoldPrice(price);
		holdsell.setHoldType(TradeTypeEnum.TT_SELL.code);
		UserHoldStock holdbuy = new UserHoldStock();
		holdbuy.setStockId(stockId);
		holdbuy.setUserId(buyRecord.getUserId());
		holdbuy.setHoldQt(RemainQt);
		holdbuy.setHoldPrice(price);
		holdbuy.setHoldType(TradeTypeEnum.TT_BUY.code);
		userHoldStockMapper.insertSelective(holdsell);
		userHoldStockMapper.insertSelective(holdbuy);
		return new AsyncResult<ConsignSellAndBuyRecord>(returnObj);
	}

}
