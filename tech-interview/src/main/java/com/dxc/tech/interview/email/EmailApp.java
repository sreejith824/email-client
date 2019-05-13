package com.dxc.tech.interview.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EmailApp {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailApp.class);
	private static final Session MAILING_SESSION = Session.getDefaultInstance(new Properties());

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	




	public static void sendHtmlEmail(String fromEmail, String toEmail, String subject, String body){
		trySendEmail(fromEmail, toEmail, subject, body, true);
	}

	public static void trySendEmail(String fromEmail, String toEmail, String subject, String body){
		trySendEmail(fromEmail, toEmail, subject, body, false);
	}

	private static void trySendEmail(String fromEmail, String toEmail, String subject, String body, boolean html){
		try{
			sendEmail(fromEmail, toEmail, subject, body, html);
		}catch(MessagingException e){
			logger.error("", e);
		}
	}

	private static void sendEmail(String fromEmail, String toEmail, String subject, String body, boolean html)
	throws MessagingException{
		MimeMessage message = new MimeMessage(MAILING_SESSION);
		message.setFrom(new InternetAddress(fromEmail));
		InternetAddress[] addresses = InternetAddress.parse(toEmail);//one or more addresses
		message.addRecipients(RecipientType.TO, addresses);
		message.setReplyTo(addresses);
		message.setSubject(subject);
		String subType;
		if(html){
			subType = "html";
		}else{
			subType = "plain";
		}
		message.setText(body, "UTF-8", subType);
		Transport.send(message);
	}


}
