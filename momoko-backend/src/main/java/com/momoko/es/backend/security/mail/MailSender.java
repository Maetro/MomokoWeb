package com.momoko.es.backend.security.mail;

/**
 * The mail sender interface for sending mail
 */
public interface MailSender<MailData> {

	void send(MailData mail);
}