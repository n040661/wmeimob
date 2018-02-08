package com.wmeimob.exception;

/**
 * 自定义授权错误异常
 * 未授权或授权失败都应该抛出此异常
 * @author zJun
 * @date 2018年2月4日 下午7:33:13
 */
public class AuthorityException extends RuntimeException {

	private static final long serialVersionUID = -6244518028589887059L;
	public String msg;
	
	public AuthorityException() {}

	public AuthorityException(String msg) {
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