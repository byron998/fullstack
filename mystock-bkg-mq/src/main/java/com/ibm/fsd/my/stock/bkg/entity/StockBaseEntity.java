package com.ibm.fsd.my.stock.bkg.entity;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import com.ibm.fsd.my.stock.bkg.domain.StockBase;

public class StockBaseEntity extends StockBase{
	@Id
	private Integer id;
	@NotNull
    private String username;
	@NotNull
    private String password;
	@NotNull
    private String mobile;

}
