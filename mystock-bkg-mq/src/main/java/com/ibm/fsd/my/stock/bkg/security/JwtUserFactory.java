package com.ibm.fsd.my.stock.bkg.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(JSONObject json) {
        return new JwtUser(
        		json.getString("id"),
        		json.getString("username"),
        		json.getString("name"),
        		"",
        		json.getString("mobile"),
        		json.getString("role"),
        		mapToGrantedAuthorities(json.getJSONArray("authorities")),
        		json.getBoolean("enabled"),
        		json.getDate("lastPasswordResetDate")
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
