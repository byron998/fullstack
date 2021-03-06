package com.ibm.fsd.my.stock.bkg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.fsd.my.stock.bkg.bean.result.VarcharBoolean;
import com.ibm.fsd.my.stock.bkg.domain.UserBase;
import com.ibm.fsd.my.stock.bkg.mapper.UserBaseCustMapper;
import com.ibm.fsd.my.stock.bkg.security.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserBaseCustMapper userBaseCustMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBase user = userBaseCustMapper.getUserByName(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User ['%s'] is not exist.", username));
        } 
        else if (user.getAvailable().equals(VarcharBoolean.BOOL_FALSE.code)) {
        	throw new UsernameNotFoundException(String.format("User ['%s'] is not available.", username));
        }
        else {
            return JwtUserFactory.create(user);
        }
    }
    
    
}
