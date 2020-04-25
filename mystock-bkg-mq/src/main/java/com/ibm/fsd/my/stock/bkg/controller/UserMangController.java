package com.ibm.fsd.my.stock.bkg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsd.my.stock.bkg.bean.po.UserBasePo;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResponse;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResult;
import com.ibm.fsd.my.stock.bkg.bean.vo.UserMangVo;
import com.ibm.fsd.my.stock.bkg.domain.UserBase;
import com.ibm.fsd.my.stock.bkg.service.UserMangService;

@RestController
@RequestMapping("/testBoot")
public class UserMangController {
	@Autowired
    private UserMangService userMangService;
	
	@GetMapping("getAllUsers")
    public List<UserBase> GetAllUsers(){
        return userMangService.getAllUsers();
    }
	
	@GetMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
		return "UserId is" + id;
    }
	
	@PostMapping("register")
	public ReturnResult<UserMangVo> register(@RequestBody UserBasePo request) {
		UserMangVo data = new UserMangVo();

		return ReturnResponse.makeOKResp(data);
	}
}
