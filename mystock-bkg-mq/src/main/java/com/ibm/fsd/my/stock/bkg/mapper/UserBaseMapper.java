package com.ibm.fsd.my.stock.bkg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ibm.fsd.my.stock.bkg.entity.UserBaseEntity;

public interface UserBaseMapper {
	 @Select("select * from user_base")
	 List<UserBaseEntity> getAllUsers();
	 
	 @Select("select * from user_base where available = 'n'")
	 List<UserBaseEntity> getUsersWaitingAvailable();
	 
	 @Select("select * from user_base where id=#{id}")
	 UserBaseEntity getUserById(Long id);
	 
	 @Select("select * from user_base where username=#{name}")
	 UserBaseEntity getUserByName(String name);
	 
	 @Update("update user_base set available = 'y' where id=#{id}")
	 UserBaseEntity updateAvailableById(Long id);
	 
	 @Insert("insert user_base values()")
	 UserBaseEntity insertUser(UserBaseEntity input);
	 
	 
}
