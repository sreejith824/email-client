package com.dxc.tech.interview.email.domain;

public class Email {
	private String toAddress;
	private String fromAddress;
	private String body;
	private String subject;
	private String contentType;
	private boolean isRetryRequired;
	private boolean isDisclaimerRequired;
	private EncryptionOption[] encryptions;

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isRetryRequired() {
		return isRetryRequired;
	}

	public void setRetryRequired(boolean isRetryRequired) {
		this.isRetryRequired = isRetryRequired;
	}

	public boolean isDisclaimerRequired() {
		return isDisclaimerRequired;
	}

	public void setDisclaimerRequired(boolean isDisclaimerRequired) {
		this.isDisclaimerRequired = isDisclaimerRequired;
	}

	public EncryptionOption[] getEncryptions() {
		return encryptions;
	}

	public void setEncryptions(EncryptionOption[] encryptions) {
		this.encryptions = encryptions;
	}

}
