package com.juven.mvnbook.account.email;
import javax.mail.MessagingException;
import javax.mail.internet.*;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
public class AccountEmailServiceImpl implements AccountEmailService{
	private JavaMailSender javaMailSender;
	private String systemEmail;
	

	public void sendMail(String to, String subject, String hemlText) throws Exception  {
		// TODO Auto-generated method stub
		try{
			MimeMessage msg=javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper=new MimeMessageHelper(msg);
			msgHelper.setFrom(systemEmail);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(hemlText,true);
			javaMailSender.send(msg);
		}
		catch(MessagingException e){
			throw new Exception("Failed to send email.",e);
		}
	}
	public JavaMailSender getJavaMailSender(){
		return javaMailSender;
	}
	public void setJavaMailSender(JavaMailSender javaMailSender){
		this.javaMailSender=javaMailSender;
		
	}
	
	public String getSystemEmail()
	{
		return systemEmail;
	}
	public void setSystemEmail(String systemEmail){
		this.systemEmail=systemEmail;
	}

}
