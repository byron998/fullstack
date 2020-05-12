package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Select;

public interface StockConsignSellRecordCustMapper extends StockConsignSellRecordMapper {
	 @Select("<script>update stock_consign_sell_record set remains_qt = remains_qt - #{decrease}"
	 		+ " <when test='isCompleted==True'>, completed='y'</when>"
			+ " where id=#{sellId}</script>")// and last_update_dt = #{locktime}
	 Integer decreaseConsignSellRemains(Long sellId, Long decrease, Boolean isCompleted, Date locktime);
}
