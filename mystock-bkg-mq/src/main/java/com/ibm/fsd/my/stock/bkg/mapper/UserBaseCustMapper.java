package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ibm.fsd.my.stock.bkg.domain.UserBase;

public interface UserBaseCustMapper extends UserBaseMapper{
	 @Select("select * from user_base where is_admin = 'n'")
	 List<UserBase> getAllUsers();
	 
	 @Select("select * from user_base where available=#{available} and is_admin = 'n'")
	 List<UserBase> getUsersByAvailable(String available);
	 
	 @Select("select * from user_base where online=#{online} and is_admin = 'n'")
	 List<UserBase> getUsersByOnline(String online);
	 
	 @Select("select count(*) from user_base where available = 'n' and is_admin = 'n'")
	 Integer cntUsersWaitingAvailable();
	 
	 @Select("select count(*) from user_base where available = 'y' and is_admin = 'n'")
	 Integer cntUsersPassAvailable();
	 
	 @Select("select count(*) from user_base where online = 'y' and is_admin = 'n'")
	 Integer cntUsersIsOnlining();
	 
	 @Update("update user_base set available = 'y' where id=#{id}")
	 Boolean makeAvailable(Long id);
	 
	 @Select("select * from user_base where username=#{name}")
	 UserBase getUserByName(String name);
	 
	 @Select("select * from user_base where username=#{name} and available = 'y'")
	 UserBase getAvailableUserByName(String name);
	 
	 @Select("select * from user_base where mobile=#{mobile}")
	 UserBase getUserByMobile(String mobile);
}
