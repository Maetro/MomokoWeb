/**
 * Mail.java 11-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    public static void sendEmail(final String asunto, final String contenido, final String destinatario) {

        final Properties props = new Properties();

        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "25");
        final Session session = Session.getDefaultInstance(props);

        try {

            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@momoko.es"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(contenido);

            Transport.send(message);

            System.out.println("Done");

        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
