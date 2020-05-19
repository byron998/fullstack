package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ibm.fsd.my.stock.bkg.domain.StockPriceHistory;

public interface StockPriceHistoryCustMapper extends StockPriceHistoryMapper{
	 @Update("UPDATE stock_consign_buy_record SET remains_qt = remains_qt - #{decrease}"
			+ " last_update_dt = CURRENT_TIMESTAMP "
	 		+ " <when test='isCompleted==True'>, stauts='02'</when>"
	 		+ " WHERE id=#{id}")// and last_update_dt = #{lock_time}
	 Integer decreaseConsignbuyRemains(Long id, Long decrease, Boolean isCompleted, Date locktime);
	 
	 @Select("SELECT spg.* FROM stock_price_history sph "
	 		+ "(SELECT stock_id, MAX(history_date) AS max_date FROM stock_price_history GROUP BY stock_id) spg "
	 		+ " WHERE sph.stock_id = spg.stock_id AND sph.history_date = spg.max_date ORDER BY stock_id")
	 List<StockPriceHistory> getLastDatePrices();
	 
	 @Select("SELECT spg.* FROM stock_price_history sph Where history_date = #{today} ORDER BY stock_id")
	 List<StockPriceHistory> getToDatePrices(String today);
	 
	 @Select("SELECT spg.* FROM stock_price_history sph Where stock_id = #{stockid} ORDER BY history_date Limit #{top}")
	 List<StockPriceHistory> getStockHistoryPrices(Long stockid, Integer top);
	 //DATE_FORMAT(NOW(), '%Y%m%d')
}
