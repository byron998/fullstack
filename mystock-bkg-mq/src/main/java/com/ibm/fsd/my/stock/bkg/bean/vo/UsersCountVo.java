package com.ibm.fsd.my.stock.bkg.bean.vo;

import lombok.Data;

@Data
public class UsersCountVo {
	private int cntWaitingAvailable;
	
	private int cntPassAvailable;
	
	private int cntIsOnlining;
}
