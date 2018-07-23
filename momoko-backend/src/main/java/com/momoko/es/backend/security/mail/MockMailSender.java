package com.momoko.es.backend.security.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A mock mail sender for 
 * writing the mails to the log.
 * 
 * @author Sanjay Patel
 */
public class MockMailSender implements MailSender<MomokoMailData> {
	
	private static final Log log = LogFactory.getLog(MockMailSender.class);
	
	public MockMailSender() {
		log.info("Created");
	}

	@Override
	public void send(MomokoMailData mail) {
		
		log.info("Sending mail to " + mail.getTo());
		log.info("Subject: " + mail.getSubject());
		log.info("Body: " + mail.getBody());
	}

}
