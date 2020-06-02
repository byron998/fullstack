package com.ibm.fsd.my.stock.bkg;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class ZuulApiApp {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiApp.class, args);
	}
}
