package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ibm.fsd.my.stock.bkg.domain.StockConsignbuyRecord;

public interface StockConsignbuyRecordCustMapper extends StockConsignbuyRecordMapper{
	 @Update("<script>UPDATE stock_consign_buy_record SET remains_qt = remains_qt - #{decrease}"
			+ " last_update_dt = CURRENT_TIMESTAMP "
	 		+ " <when test='isCompleted==True'>, stauts='02'</when>"
	 		+ " WHERE id = #{buyid} AND last_update_dt = #{lock_time}</script>")
	 Integer decreaseConsignbuyRemains(@Param("buyid")Long buyid, @Param("decrease")Long decrease, @Param("isCompleted")Boolean isCompleted, @Param("locktime")Date locktime);
	 
	 @Select("SELECT * FROM stock_consign_buy_record WHERE status=#{status} AND stock_id = #{stockid} AND consign_price = #{price} ORDER BY consign_dt LIMIT #{top}")
	 List<StockConsignbuyRecord> getTopNWaitingByPrice(@Param("status")String status, @Param("stockid")Long stockid, @Param("price")Double price, @Param("top")Integer top);
	 //DATE_FORMAT(NOW(), '%Y%m%d')
}
