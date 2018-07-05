package com.mm.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wmeimob.exception.AdminException;
import com.wmeimob.exception.AuthorityException;
import com.wmeimob.exception.SeriousException;
import com.wmeimob.util.LogUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理
 * 
 * @author zJun auto
 * @date 2018年02月57日 上午22:26:32
 */
@ControllerAdvice
@Slf4j
public class AdminExceptionHandler {

	@ExceptionHandler(value = { AuthorityException.class })
	@ResponseBody
	public ResponseEntity<?> authorityHandler(AuthorityException e, HttpServletResponse response) {
		LogUtil.error(log, "全局授权异常捕获", e);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMsg());
	}

	@ExceptionHandler(value = { AdminException.class })
	@ResponseBody
	public ResponseEntity<?> adminExceptionHandler(AdminException e, HttpServletResponse response) {
		LogUtil.error(log, "全局授权异常捕获", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMsg());
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseBody
	public ResponseEntity<?> illegalArgumentExceptionException(IllegalArgumentException e,
			HttpServletResponse response) {
		LogUtil.error(log, "全局授权异常捕获", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

	@ExceptionHandler(value = { RuntimeException.class })
	@ResponseBody
	public ResponseEntity<?> runtimeHandler(RuntimeException e, HttpServletResponse response) {
		LogUtil.error(log, "全局未知异常捕获", e);
		return ResponseEntity.status(HttpStatus.VARIANT_ALSO_NEGOTIATES).body("网络异常");
	}
	
	@ExceptionHandler(value = { SeriousException.class })
	@ResponseBody
	public ResponseEntity<?> seriousExceptionHandler(SeriousException e, HttpServletResponse response) {
		LogUtil.error(log, "全局未知异常捕获", e);
		return ResponseEntity.status(HttpStatus.VARIANT_ALSO_NEGOTIATES).body("网络异常");
	}

}
