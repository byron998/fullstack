package com.ibm.fsd.my.stock.bkg.entity;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.ibm.fsd.my.stock.bkg.bean.result.VarcharBoolean;

import lombok.Data;

@Data
public class StockBaseEntity {
	@Id
	private Integer id;
	@NotNull
    private String username;
	@NotNull
    private String password;
	@NotNull
    private String mobile;
    private VarcharBoolean available;
    private VarcharBoolean isAdmin;
    private Timestamp lastLoginDt;
    private VarcharBoolean online;
}
