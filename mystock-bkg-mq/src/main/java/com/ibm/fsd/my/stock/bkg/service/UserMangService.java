package com.ibm.fsd.my.stock.bkg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fsd.my.stock.bkg.bean.result.TradeTypeEnum;
import com.ibm.fsd.my.stock.bkg.bean.result.VarcharBoolean;
import com.ibm.fsd.my.stock.bkg.domain.UserBase;
import com.ibm.fsd.my.stock.bkg.domain.UserFund;
import com.ibm.fsd.my.stock.bkg.mapper.CommonMapper;
import com.ibm.fsd.my.stock.bkg.mapper.UserBaseCustMapper;
import com.ibm.fsd.my.stock.bkg.mapper.UserFundMapper;

@Service
public class UserMangService {
	@Autowired
	private UserBaseCustMapper userBaseCustMapper;
	@Autowired
	private UserFundMapper userFundMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	public List<UserBase> getAllUsers(){
        return userBaseCustMapper.getAllUsers();
    }
	public UserBase getUserById(Long id) {
		return userBaseCustMapper.selectByPrimaryKey(id);
	}
	public UserBase getUserByName(String name) {
		return userBaseCustMapper.getUserByName(name);
	}
	public UserBase getUserByMobile(String mobile) {
		return userBaseCustMapper.getUserByMobile(mobile);
	}
	public Integer insertUser(UserBase input) {
		userBaseCustMapper.insertSelective(input);
		return commonMapper.getLastInsertedId();
	}
	@Transactional(rollbackFor = {Exception.class})
	public Integer makeAvailableAndGetDeafultFund(UserFund input) {
		userBaseCustMapper.makeAvailable(input.getUserId());
		input.setFundType(TradeTypeEnum.TT_DEFAULT.code);
		input.setCompleted(VarcharBoolean.BOOL_TRUE.code);
		return userFundMapper.insertSelective(input);
	}
}
