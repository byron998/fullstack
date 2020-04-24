package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ibm.fsd.my.stock.bkg.entity.StockBaseEntity;

public interface StockBaseMapper {
	 @Select("select * from stock_base")
	 List<StockBaseEntity> getAllStocks();
	 
	 @Select("select * from stock_base where suspend = 'n'")
	 List<StockBaseEntity> getAllStocksWithOutSuspend();
	 
	 @Select("select * from stock_base where id=#{id}")
	 StockBaseEntity getStockById(Long id);
	 
	 @Select("select * from stock_base where stock_code=#{code}")
	 StockBaseEntity getUserByCode(String code);
	 
	 @Update("update stock_base set suspend = 'y', suspend_days = #{days}, suspend_start_dt = CURRENT_TIMESTAMP, last_update_dt = CURRENT_TIMESTAMP where id=#{id}")
	 StockBaseEntity updateSuspendById(Long id, int days);
}
