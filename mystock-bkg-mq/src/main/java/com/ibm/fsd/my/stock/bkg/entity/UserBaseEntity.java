package com.ibm.fsd.my.stock.bkg.entity;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.ibm.fsd.my.stock.bkg.bean.result.VarcharBoolean;

import lombok.Data;

@Data
public class UserBaseEntity {
	@Id
	private Integer id;
	@NotNull
    private String stockCode;
	@NotNull
    private String stockName;
	@NotNull
    private Long stockCount;
	@NotNull
	private Long stockCountTrada;
	@NotNull
	private String marketCateg;
	@NotNull
	private String industryCateg;
    private VarcharBoolean suspend;
    private int suspendDays;
    private Timestamp suspendStartDt;
    private Timestamp lastUpdateDt;
}
