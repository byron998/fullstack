package com.ibm.fsd.my.stock.bkg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResponse;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResult;
import com.ibm.fsd.my.stock.bkg.security.JwtAuthenticationRequest;
import com.ibm.fsd.my.stock.bkg.security.JwtAuthenticationResponse;
import com.ibm.fsd.my.stock.bkg.security.JwtTokenUtil;
import com.ibm.fsd.my.stock.bkg.security.JwtUser;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthorizeController {
    @Value("${jwt.header}")
    private String tokenHeader;
    
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @ApiOperation(value="login and get token")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ReturnResult<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest servletRequest, HttpSession session) throws AuthenticationException {

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (userDetails.getPassword().equals(authenticationRequest.getPassword()) && 
        		userDetails.getUsername().equals(authenticationRequest.getUsername())) {
            final String token = jwtTokenUtil.generateToken(userDetails);
            // Return the token
            return ReturnResponse.makeOKResp(new JwtAuthenticationResponse(token));
        }
        else {
        	return ReturnResponse.makeErrResp("Username or Passwrod incorrect.");
        }
    }

    @ApiOperation(value="Refresh Token")
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ReturnResult<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = jwtTokenUtil.refreshToken(token);
        return ReturnResponse.makeOKResp(new JwtAuthenticationResponse(refreshedToken));
    }

    @ApiOperation(value="Resolving Token")
    @RequestMapping(value = "me", method = RequestMethod.GET)
    public ReturnResult<JwtUser> getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return ReturnResponse.makeOKResp((JwtUser) userDetailsService.loadUserByUsername(username));
    }
    
    
}
