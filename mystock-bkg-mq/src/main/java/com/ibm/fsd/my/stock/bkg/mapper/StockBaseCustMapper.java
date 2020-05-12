package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.ibm.fsd.my.stock.bkg.domain.StockBase;

public interface StockBaseCustMapper extends StockBaseMapper{
	 @Select("select * from stock_base")
	 List<StockBase> getAllStocks();
	 
	 @Select("select * from stock_base where market_categ=#{market}")
	 List<StockBase> getAllStocksByMarketCateg(String market);
	 
	 @Select("select * from stock_base where industry_categ=#{industry}")
	 List<StockBase> getAllStocksByIndustryCateg(String industry);
	 
	 @Select("select * from stock_base where market_categ=#{market} and industry_categ=#{industry}")
	 List<StockBase> getAllStocksByMarketAndIndustryCateg(String market, String industry);
	 
	 @Select("select * from stock_base where id=#{id}")
	 StockBase getStockById(Long id);
	 
	 @Select("select * from stock_base where stock_code=#{code}")
	 StockBase getUserByCode(String code);
	 
	 @Update("update stock_base set suspend = 'y', suspend_days = #{days}, suspend_start_dt = CURRENT_TIMESTAMP, last_update_dt = CURRENT_TIMESTAMP where id=#{id}")
	 Integer updateSuspendById(Long id, int days);
}
