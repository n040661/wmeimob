package com.wmeimob.exception;

/**
 * 后台自定义异常
 * @author zJun
 * @date 2018年3月28日 上午11:11:54
 */
public class WebException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;
	
	public String msg;
	
	private WebException() {}

	private WebException(String msg) {
        super(msg);
        this.msg = msg;
    }
	
	public WebException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }
	
	public static WebException getInstance() {
        return new WebException();
    }
	
	public static WebException getInstance(String message) {
        return new WebException(message);
    }
	
	public static WebException getInstance(String message, Throwable cause) {
        return new WebException(message, cause);
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}