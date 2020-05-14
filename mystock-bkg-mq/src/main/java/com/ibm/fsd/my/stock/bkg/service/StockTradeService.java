package com.ibm.fsd.my.stock.bkg.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fsd.my.stock.bkg.bean.result.TradeTypeEnum;
import com.ibm.fsd.my.stock.bkg.domain.StockConsignSellRecord;
import com.ibm.fsd.my.stock.bkg.domain.StockConsignbuyRecord;
import com.ibm.fsd.my.stock.bkg.domain.StockTradeRecord;
import com.ibm.fsd.my.stock.bkg.domain.UserHoldStock;
import com.ibm.fsd.my.stock.bkg.mapper.StockConsignSellRecordCustMapper;
import com.ibm.fsd.my.stock.bkg.mapper.StockConsignbuyRecordCustMapper;
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
	
	@Async //异步处理
	@Transactional(rollbackFor = {Exception.class})
	public Integer decreaseConsignRemainQt(StockConsignSellRecord sellRecord, StockConsignbuyRecord buyRecord, 
			Long RemainQt, Double price, Long stockId) throws Exception{
		//StockConsignSellRecord sellRecord = stockConsignSellRecordMapper.selectByPrimaryKey(sellId);
		//StockConsignbuyRecord buyRecord = stockConsignbuyRecordMapper.selectByPrimaryKey(sellId);
		if (sellRecord.getRemainsQt() < RemainQt || buyRecord.getRemainsQt() < RemainQt) {
			return 0;
		}
		// decrease the consign remains
		int sellNum = stockConsignSellRecordMapper.decreaseConsignSellRemains(sellRecord.getId(), RemainQt, sellRecord.getRemainsQt() == RemainQt, null);//sellRecord.getLastUpdateDt);
		int buyNum = stockConsignbuyRecordMapper.decreaseConsignbuyRemains(buyRecord.getId(), RemainQt, buyRecord.getRemainsQt() == RemainQt, null);//buyRecord.getLastUpdateDt);
		if (sellNum==0 || buyNum==0) {
			throw new Exception("buy or sell not update consign.");
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
		return sellNum + buyNum;
	}

}
