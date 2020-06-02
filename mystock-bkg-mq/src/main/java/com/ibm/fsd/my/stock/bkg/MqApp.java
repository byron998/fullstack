package com.ibm.fsd.my.stock.bkg;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResponse;
import com.ibm.fsd.my.stock.bkg.bean.result.ReturnResult;

import io.swagger.annotations.ApiOperation;


@MapperScan("com.ibm.fsd.my.stock.bkg.mapper")
@SpringBootApplication
@EnableScheduling
//@EnableEurekaClient
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MqApp 
{
	
	@Bean
	public Queue helloQueue() 
	{
        return new Queue("helloQueue");
    }
	
	
	@Bean
    public HttpMessageConverters fastJsonHttpMessageConverters()
	{
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // fix Chinese UTF8 decode
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        // support media types
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }
	
    public static void main(String[] args )
    {
    	SpringApplication.run(MqApp.class, args);
    }
}
