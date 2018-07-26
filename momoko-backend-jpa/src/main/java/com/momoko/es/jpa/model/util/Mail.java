/**
 * Mail.java 11-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.jpa.model.util;

import org.apache.commons.lang.StringUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

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
            message.setContent(contenido, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Done");

        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readMailTemplate(final String path, final Charset encoding) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String replaceTagInContent(final String tag, final String with, final String content) {
        return StringUtils.replace(content, tag, with);
    }
}
