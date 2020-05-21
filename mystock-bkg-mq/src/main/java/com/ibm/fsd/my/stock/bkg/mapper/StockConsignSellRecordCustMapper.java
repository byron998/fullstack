package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ibm.fsd.my.stock.bkg.domain.StockConsignSellRecord;

public interface StockConsignSellRecordCustMapper extends StockConsignSellRecordMapper {
	 @Select("<script>UPDATE stock_consign_sell_record set remains_qt = (remains_qt - #{decrease}),"
			+ " last_update_dt = CURRENT_TIMESTAMP "
	 		+ " <when test='isCompleted==True'>, stauts='02'</when>"
			+ " where id=#{sellId} and last_update_dt = #{locktime}</script>")
	 Integer decreaseConsignSellRemains(@Param("sellId")Long sellId, @Param("decrease")Long decrease, @Param("isCompleted")Boolean isCompleted, @Param("locktime")Date locktime);
	 
	 @Select("SELECT * FROM stock_consign_sell_record WHERE status=#{status} AND stock_id = #{stockid} AND consign_price = #{price} ORDER BY consign_dt Limit #{top}")
	 List<StockConsignSellRecord> getTopNWaitingByPrice(@Param("status")String status, @Param("stockid")Long stockid, @Param("price")Double price, @Param("top")Integer top);
}
