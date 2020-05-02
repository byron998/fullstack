package com.ibm.fsd.my.stock.bkg.security;

import java.io.Serializable;
import lombok.Data;

@Data
public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}
