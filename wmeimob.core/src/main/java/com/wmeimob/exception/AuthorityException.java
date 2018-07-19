package com.wmeimob.exception;

/**
 * 自定义授权错误异常
 * 未授权或授权失败都应该抛出此异常
 * @author zJun
 * @date 2018年2月4日 下午7:33:13
 */
public class AuthorityException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;
	
	public String msg;
	
	public AuthorityException() {}

	private AuthorityException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	private AuthorityException(String message, Throwable cause) {
        super(message, cause);
    }
	
	/**
	 * 返回异常
	 * @param message
	 * @return
	 * @author zJun
	 * @date 2018年4月5日 下午10:41:05
	 */
	public static AuthorityException getInstance(String message) {
        return new AuthorityException(message);
    }
	
	/**
     * 返回异常
     * @param result
     * @return
     * @author zJun
     * @date 2018年4月5日 下午8:23:13
     */
    public static AuthorityException getInstance(String message, Throwable cause) {
            return new AuthorityException(message, cause);
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}