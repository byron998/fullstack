package com.ibm.fsd.my.stock.bkg.bean.vo;

import java.util.Date;
import org.springframework.data.annotation.Id;
//import com.couchbase.client.java.repository.annotation.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibm.fsd.my.stock.bkg.bean.result.VarcharBoolean;

import lombok.Data;
@Data
public class UserBaseVo {
	@Id
	private Long id;
    private String username;
    private String password;
    private String mobile;
    private VarcharBoolean available;
    private VarcharBoolean isAdmin;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginDt;
    private VarcharBoolean online;
}
