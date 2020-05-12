package com.ibm.fsd.my.stock.bkg.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fsd.my.stock.bkg.bean.result.VarcharBoolean;
import com.ibm.fsd.my.stock.bkg.domain.StockBase;
import com.ibm.fsd.my.stock.bkg.mapper.CommonMapper;
import com.ibm.fsd.my.stock.bkg.mapper.StockBaseCustMapper;

@Service
public class StockMangService {
	@Autowired
	private StockBaseCustMapper stockBaseCustMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	public List<StockBase> getAllStock(Map<String,String> condition){
		if (condition.containsKey("industry") && condition.containsKey("market")) {
			return stockBaseCustMapper.getAllStocksByMarketAndIndustryCateg(condition.get("market"), condition.get("industry"));
		}
		else if (condition.containsKey("industry")) {
	        return stockBaseCustMapper.getAllStocksByIndustryCateg(condition.get("industry"));
		}
		else if (condition.containsKey("market")) {
	        return stockBaseCustMapper.getAllStocksByMarketCateg(condition.get("market"));
		}
		else {
			return stockBaseCustMapper.getAllStocks();
		}
    }
	
	public StockBase getStockById(long id) {
		return stockBaseCustMapper.getStockById(id);
	}
	
	public Integer suspendStock(long id, int days) {
		return stockBaseCustMapper.updateSuspendById(id, days);
	}
	
	public Integer issueANewStock(StockBase record) {
		record.setSuspend(VarcharBoolean.BOOL_FALSE.code);
		record.setSuspendDays(0);
		stockBaseCustMapper.insertSelective(record);
		return commonMapper.getLastInsertedId();
	}
}
