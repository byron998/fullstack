package com.ibm.fsd.my.stock.bkg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		stockConsignbuyRecordMapper.insertSelective(record);
		return commonMapper.getLastInsertedId();
	}
	
	public Integer consignSell(StockConsignSellRecord record) {
		stockConsignSellRecordMapper.insertSelective(record);
		return commonMapper.getLastInsertedId();
	}
}
	
