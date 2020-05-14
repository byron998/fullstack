package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ibm.fsd.my.stock.bkg.domain.StockConsignbuyRecord;

public interface StockConsignbuyRecordCustMapper extends StockConsignbuyRecordMapper{
	 @Update("update stock_consign_buy_record set remains_qt = remains_qt - #{decrease}"
			+ " last_update_dt = CURRENT_TIMESTAMP "
	 		+ " <when test='isCompleted==True'>, stauts='02'</when>"
	 		+ " where id=#{id}")// and last_update_dt = #{lock_time}
	 Integer decreaseConsignbuyRemains(Long id, Long decrease, Boolean isCompleted, Date locktime);
	 
	 @Select("SELECT * FROM stock_consign_buy_record WHERE status='01' Order by consign_dt Limit #{top}")
	 List<StockConsignbuyRecord> getTopN(Integer top);
	 //DATE_FORMAT(NOW(), '%Y%m%d')
}
