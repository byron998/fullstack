package com.ibm.fsd.my.stock.bkg.config;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Bean
	public Docket createRestApi() {
		// 添加header参数
		ParameterBuilder ticketPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		ticketPar.name(tokenHeader).description("user token").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build(); // header中的ticket参数非必填，传空也可以
		pars.add(ticketPar.build()); // 根据每个方法名也知道当前方法在设置什么参数

//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//				.apis(RequestHandlerSelectors.basePackage("com.ibm.fsd.my.stock.bkg")).paths(PathSelectors.any())
//				.build().securitySchemes(newArrayList(apiKey())).globalOperationParameters(pars)
//				.securityContexts(securityContexts());
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.ibm.fsd.my.stock.bkg"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(newArrayList(apiKey()))
				.globalOperationParameters(pars)
				;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Boot添加Swagger2组件").description("Spring Boot添加Swagger2组件")
				.version("1.0").build();
	}

	@Bean
	SecurityScheme apiKey() {
		return new ApiKey("token", "Authorization", "header");
	}

	private List<SecurityContext> securityContexts() {
		List<SecurityContext> securityContexts = new ArrayList<SecurityContext>();
		securityContexts.add(SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("^(?!auth).*$")).build());
		return securityContexts;
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> securityReferences = new ArrayList<SecurityReference>();
		securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
		return securityReferences;
	}

}
