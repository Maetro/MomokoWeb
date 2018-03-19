/**
 * Mail.java 11-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package main;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    public static void sendEmail(final String asunto, final String contenido) {
        final String username = "info@momoko.es";
        final String password = "hachiko707";

        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@momoko.es"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("RMaetro@gmail.com"));
            message.setSubject(asunto);
            message.setText(contenido);

            Transport.send(message);

            System.out.println("Done");

        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
