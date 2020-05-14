package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ibm.fsd.my.stock.bkg.domain.StockConsignSellRecord;

public interface StockConsignSellRecordCustMapper extends StockConsignSellRecordMapper {
	 @Select("<script>update stock_consign_sell_record set remains_qt = (remains_qt - #{decrease}),"
			+ " last_update_dt = CURRENT_TIMESTAMP "
	 		+ " <when test='isCompleted==True'>, stauts='02'</when>"
			+ " where id=#{sellId}</script>")// and last_update_dt = #{locktime}
	 Integer decreaseConsignSellRemains(Long sellId, Long decrease, Boolean isCompleted, Date locktime);
	 
	 @Select("SELECT * FROM stock_consign_sell_record WHERE status='01' Order by consign_dt Limit #{top}")
	 List<StockConsignSellRecord> getTopN(Integer top);
}
