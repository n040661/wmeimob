package com.wmeimob.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.wmeimob.bean.AliSmsConf;

/**
 * 发送短信  阿里短息服务
 * @author zJun
 * @date 2017年10月21日 下午6:01:27
 */
public class AliSmsSDK {
    
    private AliSmsConf conf;
	
	private IAcsClient acsClient;
	
	public AliSmsSDK(){}
	
	public AliSmsSDK(AliSmsConf conf){
	    this.conf = conf;
		this.init();
	}
	
	private void init() {
		try {
			final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
			final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", this.conf.getAccessId(), this.conf.getAccessKey());
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			acsClient = new DefaultAcsClient(profile);
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送短信
	 * @param message
	 * @param phone
	 * @param templateCode 短信模板 必填:短信模板-可在短信控制台中找到
	 * @param signName 短信签名 （一般为公司名称）必填:短信签名-可在短信控制台中找到
	 * @author zJun
	 * @date 2018年5月2日 上午10:17:43
	 */
	public void sendCode(String message, String phone, String templateCode, String signName) {
		SendSmsRequest request = new SendSmsRequest();
		request.setMethod(MethodType.POST);
		request.setPhoneNumbers(phone);
		request.setSignName(signName);
		request.setTemplateCode(templateCode);
		request.setTemplateParam(message);
		try {
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() == null || !sendSmsResponse.getCode().equals("OK")) {
				throw new RuntimeException("发送短信失败:"+sendSmsResponse.getMessage());
			}
		} catch (ServerException e) {
			throw new RuntimeException("发送短信失败,网络异常");
		} catch (ClientException e) {
			throw new RuntimeException("发送短信失败,网络异常");
		}
	}
	
	/**
	 * 发送短信
     * @param message
     * @param phone
     * @param templateCode 短信模板 必填:短信模板-可在短信控制台中找到
     * @author zJun
	 * @date 2018年5月2日 下午1:38:45
	 */
	public void sendCode(String message, String phone, String templateCode) {
        sendCode(message, phone, templateCode, this.conf.getSignName());
    }
	
	public static void main(String[] args)  {
		final String accessId = "LTAIdhpc8WZIrQTi";//你的accessKeyId,参考本文档步骤2
		final String accessKey = "eYgkheCwVfagT2y1T9iUAzz76N6k5X";//你的accessKeySecret，参考本文档步骤2
		String tempCode = "SMS_134075001";
		AliSmsSDK sam = new AliSmsSDK(new AliSmsConf(accessId, accessKey, "美萌软件"));
//		sam.sendCode("123456", "18018840522");
		sam.sendCode("654321", "17621451376", tempCode, "美萌软件");
	}
	
}
