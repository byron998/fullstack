package com.ibm.fsd.my.stock.bkg.bean.result;

public class ReturnResponse {
	private final static String SUCCESS = "success";

	public static <T> ReturnResult<T> makeOKResp() {
		return new ReturnResult<T>().setCode(ReturnCode.SUCCESS).setMsg(SUCCESS);
	}

	public static <T> ReturnResult<T> makeOKResp(T data) {
		return new ReturnResult<T>().setCode(ReturnCode.SUCCESS).setMsg(SUCCESS).setData(data);
	}

	public static <T> ReturnResult<T> makeErrResp(String message) {
		return new ReturnResult<T>().setCode(ReturnCode.FAIL).setMsg(SUCCESS);
	}

	public static <T> ReturnResult<T> makeErrResp(int code, String msg) {
		return new ReturnResult<T>().setCode(code).setMsg(msg);
	}

	public static <T> ReturnResult<T> makeErrResp(int code, String msg, T data) {
		return new ReturnResult<T>().setCode(code).setMsg(msg).setData(data);
	}
}
