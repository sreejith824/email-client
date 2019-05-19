package com.tech.interview.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dxc.tech.interview.email.domain.Email;
import com.dxc.tech.interview.email.domain.EncryptionOption;

public class EmailApp {

	private static final Logger logger = LoggerFactory.getLogger(EmailApp.class);
	private static final Session MAILING_SESSION = Session.getDefaultInstance(new Properties());

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void sendPlainEmailToOutsideWithDisclaimer() throws MessagingException {
		Email email = new Email();
		email.setFromAddress("from@test.com");
		email.setToAddress("to@test.com");
		email.setBody("Body");
		email.setContentType("plain");
		email.setDisclaimerRequired(true);
		email.setRetryRequired(false);
		sendEmail(email);
	}
	
	public static void sendHtmlEmailToInternalWithEncryptionRetry(String fromEmail, String toEmail, String subject,
			String body) throws MessagingException {
		Email email = new Email();
		email.setFromAddress("from@test.com");
		email.setToAddress("to@test.com");
		email.setBody("Body");
		email.setContentType("html");
		email.setRetryRequired(true);
		EncryptionOption [] encryptionOptions = {EncryptionOption.AES};
		email.setEncryptions(encryptionOptions);
		email.setRetryRequired(true);
		sendEmail(email);
	}

	public static void trySendEmail(String fromEmail, String toEmail, String subject, String body) {
		trySendEmail(fromEmail, toEmail, subject, body, false);
	}

	private static void trySendEmail(String fromEmail, String toEmail, String subject, String body, boolean html) {
		try {
			sendEmail(fromEmail, toEmail, subject, body, html);
		} catch (MessagingException e) {
			logger.error("", e);
		}
	}

	private static void sendEmail(Email email) throws MessagingException{
		MimeMessage message = new MimeMessage(MAILING_SESSION);
		message.setFrom(new InternetAddress(email.getFromAddress()));
		InternetAddress toAddress = new InternetAddress(email.getToAddress());
		String hostName = MAILING_SESSION.getProperty("mail.host");

		InternetAddress[] addresses = InternetAddress.parse(email.getToAddress());// one or more addresses
		message.addRecipients(RecipientType.TO, addresses);
		message.setSubject(email.getSubject());
		StringBuilder mailBody = new StringBuilder(email.getBody());
		if (email.isDisclaimerRequired()) {
			mailBody.append("Disclaimer");
		}
		
		if(email.getEncryptions().length > 0) {
			
		}
		
		message.setText(mailBody.toString(), "UTF-8", email.getContentType());
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (email.isRetryRequired()) {
				sendEmail(email);
			}
		}
	}

}
