package com.wmeimob.exception;

/**
 * 后台自定义异常
 * @author zJun
 * @date 2018年3月28日 上午11:11:54
 */
public class AdminException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;
	
	public String msg;
	
	public AdminException() {}

	public AdminException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}