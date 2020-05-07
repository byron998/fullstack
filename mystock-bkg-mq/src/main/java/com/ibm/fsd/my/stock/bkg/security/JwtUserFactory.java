package com.ibm.fsd.my.stock.bkg.security;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.fsd.my.stock.bkg.bean.result.VarcharBoolean;
import com.ibm.fsd.my.stock.bkg.domain.UserBase;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserBase base) {
    	Calendar cal = Calendar.getInstance();
    	cal.add(cal.DATE, -1);
        return new JwtUser(
        		base.getId(),
        		base.getUsername(),
        		base.getPassword(),
        		base.getMobile(),
        		base.getIsAdmin().equals(VarcharBoolean.BOOL_TRUE.code) ? JwtUserRoleEnum.ADMIN: JwtUserRoleEnum.USER,
        		base.getAvailable().equals(VarcharBoolean.BOOL_TRUE.code) ? true: false,
        		null
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(JSONArray authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for(int i=0;i < authorities.size();i++) {
        	JSONObject json = authorities.getJSONObject(i);
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(json.getString("name"));
            grantedAuthorities.add(grantedAuthority);
        }
        return grantedAuthorities;
    }
}
