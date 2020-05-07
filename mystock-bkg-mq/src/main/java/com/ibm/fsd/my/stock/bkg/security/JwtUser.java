package com.ibm.fsd.my.stock.bkg.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class JwtUser implements UserDetails {

    private static final long serialVersionUID = -7943208114673163534L;

    private final Long id;
    private final String username;
    private final String password;
    private final String mobile;
    private final boolean isEnabled;
    private final Date lastPwdResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
    		Long id,
            String username,
            String password, 
            String mobile,
            JwtUserRoleEnum role,
            Boolean isEnabled,
            Date lastPwdResetDate
    ) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(role.getRole()));
        this.isEnabled = isEnabled;
        this.lastPwdResetDate = lastPwdResetDate;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    //@Override
    public String getUsername() {
        return username;
    }
    
    @JsonIgnore
    //@Override
    public String getPassword() {
        return password;
    }
    
    @JsonIgnore
    //@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    //@Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    //@Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	public Date getLastPwdResetDate() {
		return lastPwdResetDate;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

}
