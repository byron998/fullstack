package com.ibm.fsd.my.stock.bkg.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fsd.my.stock.bkg.bean.result.TradeStatusEnum;
import com.ibm.fsd.my.stock.bkg.domain.StockConsignSellRecord;
import com.ibm.fsd.my.stock.bkg.domain.StockConsignbuyRecord;
import com.ibm.fsd.my.stock.bkg.mapper.CommonMapper;
import com.ibm.fsd.my.stock.bkg.mapper.StockConsignSellRecordCustMapper;
import com.ibm.fsd.my.stock.bkg.mapper.StockConsignbuyRecordCustMapper;

@Service
public class StockConsignService {
	@Autowired
	private StockConsignbuyRecordCustMapper stockConsignbuyRecordMapper;
	@Autowired
	private StockConsignSellRecordCustMapper stockConsignSellRecordMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	
	public Integer consignBuy(StockConsignbuyRecord record) {
		record.setStatus(TradeStatusEnum.TS_CONSIGN.code);
		record.setRemainsQt(record.getConsignQt());
		record.setLastUpdateDt(new Date());
		stockConsignbuyRecordMapper.insertSelective(record);
		return commonMapper.getLastInsertedId();
	}
	
	public Integer consignSell(StockConsignSellRecord record) {
		record.setStatus(TradeStatusEnum.TS_CONSIGN.code);
		record.setRemainsQt(record.getConsignQt());
		record.setLastUpdateDt(new Date());
		stockConsignSellRecordMapper.insertSelective(record);
		return commonMapper.getLastInsertedId();
	}
}
