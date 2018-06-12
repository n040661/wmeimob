package com.wmeimob.exception;

/**
 * 危险的异常
 * 比较严重的情况或者出乎意料的情况请抛出此异常，参数类错误不要抛出此异常
 * @author zJun
 * @date 2018年3月28日 上午11:11:54
 */
public class SeriousException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;
	
	public String msg;
	
	private SeriousException() {}

	private SeriousException(String msg) {
        super(msg);
        this.msg = msg;
    }
	
	public SeriousException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }
	
	public static SeriousException getInstance() {
        return new SeriousException();
    }
	
	public static SeriousException getInstance(String message) {
        return new SeriousException(message);
    }
	
	public static SeriousException getInstance(String message, Throwable cause) {
        return new SeriousException(message, cause);
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}