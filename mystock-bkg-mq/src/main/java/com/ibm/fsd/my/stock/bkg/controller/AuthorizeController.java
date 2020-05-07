package com.ibm.fsd.my.stock.bkg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @ApiOperation(value="token登录认证")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    //@PreAuthorize("hasPermission('user', 'update')")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest servletRequest, HttpSession session) throws AuthenticationException {

        // Perform the security
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authenticationRequest.getUsername(),
//                        authenticationRequest.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
    	// final Device device = DeviceUtils.getCurrentDevice(servletRequest);
        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (userDetails.getPassword().equals(authenticationRequest.getPassword()) && 
        		userDetails.getUsername().equals(authenticationRequest.getUsername())) {
            final String token = jwtTokenUtil.generateToken(userDetails);
            final String role = userDetails.getAuthorities().toString();
            // Return the token
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }
        else {
        	return ResponseEntity.badRequest().body("Passwrod is not much.");
        }

    }

    @ApiOperation(value="刷新重新获取授权token")
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        //String username = jwtTokenUtil.getUsernameFromToken(token);
        //JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        String refreshedToken = jwtTokenUtil.refreshToken(token);
        return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    }

    @ApiOperation(value="获取当前用户")
    @RequestMapping(value = "me", method = RequestMethod.GET)
    public ResponseEntity<JwtUser> getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return ResponseEntity.ok((JwtUser) userDetailsService.loadUserByUsername(username));
    }
    
    
}
