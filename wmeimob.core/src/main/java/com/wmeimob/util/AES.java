package com.wmeimob.util;

import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信小程序AES解密
 * 参考: https://blog.csdn.net/wujin274/article/details/76604964
 * @author zJun
 * @date 2018年6月11日 下午4:20:04
 */
@Slf4j
public class AES {
	public static boolean initialized = false;

	/**
	 * AES解密
	 * @param content 密文
	 * @param keyByte sessionKey
	 * @param ivByte iv
	 * @return
	 * @author zJun
	 * @date 2018年6月11日 下午4:22:48
	 */
	public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
		initialize();
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			Key sKeySpec = new SecretKeySpec(keyByte, "AES");

			cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (NoSuchAlgorithmException e) {
			LogUtil.error(log, "AES解密错误", e);
		} catch (NoSuchPaddingException e) {
			LogUtil.error(log, "AES解密错误", e);
		} catch (InvalidKeyException e) {
			LogUtil.error(log, "AES解密错误", e);
		} catch (IllegalBlockSizeException e) {
			LogUtil.error(log, "AES解密错误", e);
		} catch (BadPaddingException e) {
			LogUtil.error(log, "AES解密错误", e);
		} catch (NoSuchProviderException e) {
			LogUtil.error(log, "AES解密错误", e);
		} catch (Exception e) {
			LogUtil.error(log, "AES解密错误", e);
		}
		return null;
	}

	public static void initialize() {
		if (initialized)
			return;
		Security.addProvider(new BouncyCastleProvider());
		initialized = true;
	}

	// 生成iv
	public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
		AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
		params.init(new IvParameterSpec(iv));
		return params;
	}
}
