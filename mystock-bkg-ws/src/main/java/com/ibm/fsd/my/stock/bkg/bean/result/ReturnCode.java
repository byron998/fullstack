package com.ibm.fsd.my.stock.bkg.bean.result;

public enum ReturnCode {
	// 成功
	SUCCESS(200),

	// 失败
	FAIL(400),

	// 未认证（签名错误）
	UNAUTHORIZED(401),

	// 接口不存在
	NOT_FOUND(404),

	// 服务器内部错误
	INTERNAL_SERVER_ERROR(500),

	// 不在服务区间
	NOTIN_SERVICE_PERIOD(505);
	
	public int code;

	ReturnCode(int code) {
		this.code = code;
	}
}
