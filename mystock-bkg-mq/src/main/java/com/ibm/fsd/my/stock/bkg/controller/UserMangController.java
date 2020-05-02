package com.ibm.fsd.my.stock.bkg.controller;

import java.util.List;

import javax.validation.constraints.DecimalMin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsd.my.stock.bkg.bean.ao.UserBaseAo;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnCode;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResponse;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResult;
import com.ibm.fsd.my.stock.bkg.bean.vo.UserBaseVo;
import com.ibm.fsd.my.stock.bkg.domain.UserBase;
import com.ibm.fsd.my.stock.bkg.domain.UserFund;
import com.ibm.fsd.my.stock.bkg.service.UserMangService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/user")
public class UserMangController {
	@Autowired
    private UserMangService userMangService;
	
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value="用户获取全部")
	@GetMapping("all")
    public List<UserBase> GetAllUsers(){
        return userMangService.getAllUsers();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value="用户获取")
	@GetMapping("{id}")
    public ReturnResult<UserBaseVo> GetUser(@PathVariable long id){
		UserBaseVo data = new UserBaseVo();
		try {
			UserBase result = userMangService.getUserById(id);
			if (result == null || result.getId() == 0l) {
				return ReturnResponse.makeErrResp(ReturnCode.NOT_FOUND.code, "数据未找到");
			}
			BeanUtils.copyProperties(result, data);
		}
		catch(Exception ex) {
			return ReturnResponse.makeErrResp(ReturnCode.INTERNAL_SERVER_ERROR.code, "数据库操作异常");
		}
		return ReturnResponse.makeOKResp(data);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value="用户启用")
	@PostMapping("enabled/{id}")
	public ReturnResult<UserBaseVo> effective(@PathVariable long id, @RequestBody @Validated @DecimalMin("1000") int fund) {
		UserBaseVo data = new UserBaseVo();
		try {
			UserBase result = userMangService.getUserById(id);
			if (result == null || result.getId() == 0l) {
				return ReturnResponse.makeErrResp(ReturnCode.NOT_FOUND.code, "数据未找到");
			}
			BeanUtils.copyProperties(result, data);
			UserFund input = new UserFund();
			input.setUserId(id);
			input.setFundPrice(Integer.valueOf(fund).doubleValue());
			userMangService.makeAvailableAndGetDeafultFund(input);
		}
		catch(Exception ex) {
			return ReturnResponse.makeErrResp(ReturnCode.INTERNAL_SERVER_ERROR.code, "数据库操作异常");
		}
		return ReturnResponse.makeOKResp(data);
	}
	
    @ApiOperation(value="用户注册")
	@PostMapping("register")
	public ReturnResult<UserBaseVo> register(@RequestBody @Validated UserBaseAo request) {
		UserBaseVo data = new UserBaseVo();
		
		try {
			UserBase result = userMangService.getUserByName(request.getUsername());
			if (result != null && result.getId() > 0l) {
				return ReturnResponse.makeErrResp("用户名已经被注册更换其他用户名再注册");
			}
			UserBase result2 = userMangService.getUserByMobile(request.getMobile());
			if (result2 != null && result2.getId() > 0l) {
				return ReturnResponse.makeErrResp("手机号已经被注册更换其他手机号再注册");
			}
			UserBase input = new UserBase();
			input.setUsername(request.getUsername());
			input.setPassword(request.getPassword());
			input.setMobile(request.getMobile());
			input.setIsAdmin("n");
			BeanUtils.copyProperties(input, data);
			int insertid = userMangService.insertUser(input);
			data.setId((long)insertid);
		}
		catch(Exception ex) {
			return ReturnResponse.makeErrResp(ReturnCode.INTERNAL_SERVER_ERROR.code, "数据库操作异常");
		}
		return ReturnResponse.makeOKResp(data);
	}
}
