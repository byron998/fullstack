package com.ibm.fsd.my.stock.bkg.bean.bo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserBaseBo {
	@NotNull
	@NotBlank(message = "用户名不能为空")
    private String username;
	@NotBlank (message = "密码不能为空")
	@Size(min = 6, max = 8, message = "密码长度为6-8位")
    private String password;
	@NotBlank (message = "手机号不能为空")
	@Size(min = 11, max = 11, message = "手机号长度为11位")
	@Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号不正确")
    private String mobile;
}
