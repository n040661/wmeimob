package com.wmeimob.util;

import java.io.File;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtils {

	private static JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	
	private static String FROM = "guangxing.yang@wmeimob.com";
	
	static{
		final String smtpPort = "465";
	 	Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", "smtp.exmail.qq.com");   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        
		javaMailSender.setJavaMailProperties(props);
		javaMailSender.setUsername(FROM);
		javaMailSender.setPassword("Ysk164729");
	}
	
	
	public static void main(String[] args) throws MessagingException {
		sendMail("美萌邮件测试","啊哈哈","jun.zhou@wmeimob.com");
	}
	
	
	/**
	 * 发送文本内容带附件
	 * @param subject 主题
	 * @param contentText 内容
	 * @param to 接收人【支持用逗号分隔】
	 * @throws MessagingException
	 */
	public static void sendMail(String subject, String contentText, String to, String path, String fileName)
			throws MessagingException {
		// 配置文件，用于实例化java.mail.session
		Properties pro = System.getProperties();
		// 登录SMTP服务器,需要获得授权，网易163邮箱新近注册的邮箱均不能授权。
		// 测试 sohu 的邮箱可以获得授权
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.socketFactory.port", "465");
		pro.put("mail.smtp.socketFactory.fallback", "false");

		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mm, true);
		helper.addAttachment(fileName, new File(path + fileName));
		helper.setFrom(FROM);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(contentText);
		javaMailSender.send(mm);
	}

	/**
	 * 发送文本内容
	 * @param subject 主题
	 * @param contentText 内容
	 * @param to 接收人【支持用逗号分隔】
	 * @param cc 抄送人【支持用逗号分隔】
	 * @throws MessagingException
	 */
	public static void sendMail(String subject, String contentText, String to) throws MessagingException{
		 //配置文件，用于实例化java.mail.session    
        Properties pro = System.getProperties();    
		//登录SMTP服务器,需要获得授权，网易163邮箱新近注册的邮箱均不能授权。    
        //测试 sohu 的邮箱可以获得授权    
        pro.put("mail.smtp.auth", "true");    
        pro.put("mail.smtp.socketFactory.port", "465");    
        pro.put("mail.smtp.socketFactory.fallback", "false");    
        
		MimeMessage mm = javaMailSender.createMimeMessage();
		mm.setFrom(FROM);
		mm.addRecipients(RecipientType.TO, to);
		mm.setSubject(subject);
		mm.setText(contentText);
		javaMailSender.send(mm);
	}
}