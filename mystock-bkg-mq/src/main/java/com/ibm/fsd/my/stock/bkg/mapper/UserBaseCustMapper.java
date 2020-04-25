package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ibm.fsd.my.stock.bkg.domain.UserBase;
import com.ibm.fsd.my.stock.bkg.entity.UserBaseEntity;

public interface UserBaseCustMapper extends UserBaseMapper{
	 @Select("select * from user_base")
	 List<UserBase> getAllUsers();
	 
	 @Select("select * from user_base where available = 'n'")
	 List<UserBase> getUsersWaitingAvailable();
	 
	 @Select("select * from user_base where username=#{name}")
	 UserBase getUserByName(String name);
	 

}
