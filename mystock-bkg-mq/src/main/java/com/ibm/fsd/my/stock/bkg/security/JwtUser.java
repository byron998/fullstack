package com.ibm.fsd.my.stock.bkg.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class JwtUser implements UserDetails {

    private static final long serialVersionUID = -7943208114673163534L;

    private final String id;
    private final String username;
    private final String name;
    private final String password;
    private final String mobile;
    private final String role;

    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final Date lastPasswordResetDate;


    public JwtUser(
    		String id,
            String username,
            String name,
            String password, 
            String mobile,
            String role, 
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Date lastPasswordResetDate
    ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.role = role;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public String getId() {
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


    public String getName() {
        return name;
    }


    @JsonIgnore
    //@Override
    public String getPassword() {
        return password;
    }

    //@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //@Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }


}
