package com.juven.mvnbook.account.email;

import static junit.framework.Assert.assertEquals;

import javax.mail.Message;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icegreen.greenmail.util.*;
public class AccountEmailServiceTest {
	private GreenMail greenMail;
	
	@Before
	public void startMailServer()throws Exception{
		greenMail=new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("test@juvenxu.com", "123456");
		greenMail.start();
	}
	@Test
	public void testSendMail()throws Exception{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("account-email.xml");
		AccountEmailService accountEmailService=(AccountEmailService)ctx.getBean("accountEmailService");
		
		String subject="Test subject";
		String htmlText="<h3>Test<h3>";
		try{
		accountEmailService.sendMail("test@juvenxu.com", subject, htmlText);
		}
		catch(Exception e){
			
		}
		greenMail.waitForIncomingEmail(2000, 1);
		Message[]msgs=greenMail.getReceivedMessages();
		assertEquals(1,msgs.length);
		assertEquals(subject,msgs[0].getSubject());
		assertEquals(htmlText,GreenMailUtil.getBody(msgs[0]));
	}
	@After
	public void stopMailServer()throws Exception{
		greenMail.stop();
	}
}
