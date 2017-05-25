package com.juven.mvnbook.account.email;

public interface AccountEmailService {
       void sendMail(String to ,String subject, String hemlText)throws Exception;

	
}
