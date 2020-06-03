package com.ibm.fsd.my.stock.bkg.bean.result;

public class ReturnResult<T> {
	public int code;

	private String msg;

	private T data;

	public ReturnResult<T> setCode(ReturnCode retCode) {
		this.code = retCode.code;
		return this;
	}

	public int getCode() {
		return code;
	}

	public ReturnResult<T> setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ReturnResult<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {
		return data;
	}

	public ReturnResult<T> setData(T data) {
		this.data = data;
		return this;
	}
}
