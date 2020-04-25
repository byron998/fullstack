package com.ibm.fsd.my.stock.bkg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.fsd.my.stock.bkg.domain.UserBase;
import com.ibm.fsd.my.stock.bkg.mapper.UserBaseCustMapper;

@Service
public class UserMangService {
	@Autowired
	private UserBaseCustMapper userMangCustMapper;
	
	public List<UserBase> getAllUsers(){
        return userMangCustMapper.getAllUsers();
        
    }
	public UserBase getUserByName(String name) {
		return userMangCustMapper.getUserByName(name);
	}
	public Integer insertUser(UserBase input) {
		return userMangCustMapper.insertSelective(input);
	}
}
