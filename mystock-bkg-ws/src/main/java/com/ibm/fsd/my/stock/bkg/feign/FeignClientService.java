package com.ibm.fsd.my.stock.bkg.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResult;

import feign.Param;

@FeignClient(name= "mystock-backgroud-mq")
public interface FeignClientService {
	
	@GetMapping("/api/test/appname")
	ReturnResult<?> getAppName();

	@RequestMapping(value = "/api/auth/me",headers = {"app=mystock-backgroud-mq","token={test-app.token}"})
	ReturnResult<?> getMe(@Param("test-app.token") String type);
}
