package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Select;

public interface StockConsignbuyRecordCustMapper extends StockConsignbuyRecordMapper{
	 @Select("update stock_consign_buy_record set remains_qt = remains_qt - #{decrease}"
			+ " <when test='isCompleted==True'>, completed='y'</when>"
	 		+ " where id=#{id}")// and last_update_dt = #{lock_time}
	 Integer decreaseConsignbuyRemains(Long id, Long decrease, Boolean isCompleted, Date locktime);
	 
	 //DATE_FORMAT(NOW(), '%Y%m%d')
}
