package com.wmeimob.exception;

/**
 * 后台自定义异常
 * @author zJun
 * @date 2018年3月28日 上午11:11:54
 */
public class AdminException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;
	
	public String msg;
	
	private AdminException() {}

	private AdminException(String msg) {
        super(msg);
        this.msg = msg;
    }
	
	public AdminException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }
	
	public static AdminException getInstance() {
        return new AdminException();
    }
	
	public static AdminException getInstance(String message) {
        return new AdminException(message);
    }
	
	public static AdminException getInstance(String message, Throwable cause) {
        return new AdminException(message, cause);
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}