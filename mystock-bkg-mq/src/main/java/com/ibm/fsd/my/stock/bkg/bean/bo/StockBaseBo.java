package com.ibm.fsd.my.stock.bkg.bean.bo;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class StockBaseBo {
	@NotNull(message = "股票名称不能为空")
	@NotBlank(message = "股票名称不能为空")
    private String stockName;
	@NotBlank(message = "股票代码不能为空")
	@Size(min = 6, max = 6, message = "股票代码长度为6位")
	@Pattern(regexp = "\\d{6}$", message = "股票代码必须为6位数字")
	private String stockCode;
	@NotNull(message = "总股本数不能为空")
	private Long stockCount;
	@NotNull(message = "可交易股本数不能为空")
	private Long stockCountTrada;
	@NotBlank(message = "市场分类不能为空")
	private String marketCateg;
	@NotBlank(message = "行业分类不能为空")
	private String industryCateg;
	@NotNull(message = "股票价格不能为空")
	@Digits(integer=5,fraction=2,message="股票价格最大为5位整数，2位小数")
	@DecimalMin(value="0.01", message="股票价格范围为0.01~99999.99")
	@DecimalMax(value="99999.99", message="股票价格范围为0.01~99999.99")
	private Double issuePrice;
}
