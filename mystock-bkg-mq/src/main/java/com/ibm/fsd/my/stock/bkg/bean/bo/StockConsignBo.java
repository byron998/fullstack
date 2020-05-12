package com.ibm.fsd.my.stock.bkg.bean.bo;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.ibm.fsd.my.stock.bkg.bean.result.TradeTypeEnum;

import lombok.Data;

@Data
public class StockConsignBo {
	@NotNull(message = "股票ID不能为空")
    private Long stockId;
	@NotNull(message = "用户ID不能为空")
    private Long userId;
	@NotNull(message = "委托类型不能为空")
	private TradeTypeEnum consignType;
	@NotNull(message = "委托价格不能为空")
	@Digits(integer=5,fraction=2,message="委托价格最大为5位整数，2位小数")
	@DecimalMin(value="0.01", message="委托价格范围为0.01~99999.99")
	@DecimalMax(value="99999.99", message="委托价格范围为0.01~99999.99")
	private Double consignPrice;
	@NotNull(message = "委托数量不能为空")
	@Range(min=100,max=1000000,message="委托数量为100到100w")
	@Pattern(regexp = "^[1-9]\\d*00$", message = "委托数量为100的倍数")
	private Long consignQt;
}
