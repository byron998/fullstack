package com.ibm.fsd.my.stock.bkg.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResponse;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResult;

import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping(value = "/api/test")
public class AppTestController {

    @ApiOperation(value="获取服务名称")
	@GetMapping("appname")
    public ReturnResult<Map<String, String>> getAppName() {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("key", System.getenv("spring.application.name"));
    	return ReturnResponse.makeOKResp(map);
    }
}
