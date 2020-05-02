package com.ibm.fsd.my.stock.bkg.mapper;

import org.apache.ibatis.annotations.Select;

public interface CommonMapper {
	@Select("SELECT LAST_INSERT_ID()")
	Integer getLastInsertedId();
}
