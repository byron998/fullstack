package com.ibm.fsd.my.stock.bkg;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringCloudApplication
public class HystrixDashboardApp {
	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardApp.class, args);
	}
}
