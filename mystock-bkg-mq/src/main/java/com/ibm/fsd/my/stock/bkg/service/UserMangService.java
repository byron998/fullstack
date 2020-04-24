package com.ibm.fsd.my.stock.bkg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fsd.my.stock.bkg.entity.UserBaseEntity;
import com.ibm.fsd.my.stock.bkg.mapper.UserBaseMapper;

@Service
public class UserMangService {
	@Autowired
	private UserBaseMapper userMangMapper;
	
	public List<UserBaseEntity> getAllUsers(){
        return userMangMapper.getAllUsers();
    }
	

}
