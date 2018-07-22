package com.momoko.es.commons.mail;

/**
 * Data needed for sending a mail.
 * Override this if you need more data to be sent.
 */
public class MomokoMailData {
	
	private String to;
	private String subject;
	private String body;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public static MomokoMailData of(String to, String subject, String body) {
		
		MomokoMailData data = new MomokoMailData();
		
		data.to = to;
		data.subject = subject;
		data.body = body;

		return data;
	}
}
