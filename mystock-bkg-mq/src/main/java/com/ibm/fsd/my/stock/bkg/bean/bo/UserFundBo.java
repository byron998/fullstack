package com.ibm.fsd.my.stock.bkg.bean.bo;

import javax.validation.constraints.DecimalMin;

import lombok.Data;

@Data
public class UserFundBo {
	@DecimalMin("1000")
	private Long userid;
}
