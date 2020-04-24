package com.ibm.fsd.my.stock.bkg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsd.my.stock.bkg.schedule.MyStockTradeTask;

@RestController
@RequestMapping("/stock/trade")
public class StockTradeController {
	
	@Autowired
    ApplicationContext applicationContext;
	
	
	@PostMapping("getStatus")
	public Boolean getStatus(){
		MyStockTradeTask task = applicationContext.getBean(MyStockTradeTask.class);
        return task.selfTradeFlg;
    }
	
	@PostMapping("buy")
	public Boolean doTradeBuy(){
		MyStockTradeTask task = applicationContext.getBean(MyStockTradeTask.class);
		if (task.selfTradeFlg) {
			
		}
		else {
			
		}
        return task.selfTradeFlg;
    }

}
