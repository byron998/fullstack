package com.ibm.fsd.my.stock.bkg.schedule;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.fsd.my.stock.bkg.bean.Mail;
import com.ibm.fsd.my.stock.bkg.config.RabbitmqConfig;
import com.ibm.fsd.my.stock.bkg.domain.StockPriceHistory;
import com.ibm.fsd.my.stock.bkg.service.StockTradeService;

@Component
public class MyStockTradeTask {
	@Autowired
	private StockTradeService stockTradeService;
	@Autowired
    private ObjectMapper objectMapper;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
    private Environment env;
	
	public boolean selfTradeFlg;
	public String tradingDate;
	public List<StockPriceHistory> sotckPrices;
	public MyStockTradeTask() {
		this.selfTradeFlg = false;
	}
	
	@Scheduled(cron = "0 15 15 ? * MON-FRI")
	//@Scheduled(cron = "0 30 9 ? * MON-FRI") //every working day AM 09:30
	public void MyStockTradeStartTask(){
		System.out.println("The trade is start at " + new Timestamp(System.currentTimeMillis()).toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		this.tradingDate = sdf.format(new Date());
		this.sotckPrices = stockTradeService.getLastDatePrices();
		// first trade
		// open market
		// use bean flg set trade is start.
		this.selfTradeFlg = true;
	}
	
	@Scheduled(cron = "0 58 18 ? * MON-FRI")
	//@Scheduled(cron = "0 0 15 ? * MON-FRI") //every working day PM 15:00
	public void MyStockTradeCloseTask(){
		//use bean flg set trade is close.
		this.selfTradeFlg = false;
		System.out.println("The trade is close at " + new Timestamp(System.currentTimeMillis()).toString());
	}
	@Scheduled(cron = "*/15 * 9-18 ? * MON-FRI") //every working day AM 09:30 ~ PM 15:00 every 15 seconds
	//@Scheduled(cron = "*/15 * 9-15 ? * MON-FRI") //every working day AM 09:30 ~ PM 15:00 every 15 seconds
	public void MyStockOnTradingTask(){
		if (this.selfTradeFlg) {
			System.out.println("The market is trading at " + new Timestamp(System.currentTimeMillis()).toString());
			//stockTradeService.getTopNbuyByPrice(top, price);
			//stockTradeService.getTopNSellByPrice(top, price);
			MyStockThread thread1 = new MyStockThread("20200520");
			TimerThread thread2 = new TimerThread();
			Thread tt1 = new Thread(thread1,"mainthread");
			Thread tt2 = new Thread(thread2,"timethread");
			try {
				tt1.start();
				tt2.start();
				if (tt2.isInterrupted()) {
					thread1.stop();
				}
				//
				rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(RabbitmqConfig.EXCHANGE_A);
                rabbitTemplate.setRoutingKey(RabbitmqConfig.ROUTINGKEY_A);
                Mail bean = new Mail();
                Message message=MessageBuilder.withBody(objectMapper.writeValueAsBytes(bean)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
                message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON); 
                rabbitTemplate.convertAndSend(message); 

			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
