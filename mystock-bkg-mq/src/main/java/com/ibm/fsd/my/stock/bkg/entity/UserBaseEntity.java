package com.ibm.fsd.my.stock.bkg.entity;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import com.ibm.fsd.my.stock.bkg.domain.UserBase;

public class UserBaseEntity extends UserBase{
	@Id
	private Long id;
	@NotNull
    private String username;
	@NotNull
	private String password;
}
