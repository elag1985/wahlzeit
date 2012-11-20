package org.wahlzeit.services;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class DefaultEmailServer extends AbstractEmailServer {

	/**
	 * 
	 */
	protected DefaultEmailServer() {
		super();
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "localhost");
	    session = Session.getDefaultInstance(properties, null);
	}
	
	/**
	 * 
	 * @methodproperties composed
	 */
	public synchronized void sendEmail(EmailAddress from, EmailAddress to, EmailAddress bcc, String subject, String body) {
		try {
			Message msg = createMessage(from, to, bcc, subject, body);
			doSendEmail(msg);
		} catch (Exception ex) {
			SysLog.logThrowable(ex);
		}
	}

	/**
	 * 
	 * @methodtype factory
	 * @methodproperties composed
	 */
	protected Message createMessage(EmailAddress from, EmailAddress to,
			EmailAddress bcc, String subject, String body)
			throws MessagingException, AddressException {
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from.asString()));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to.asString()));

		if (bcc != EmailAddress.NONE) {
			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc.asString()));
		}

		msg.setSubject(subject);
		msg.setContent(createMultipart(body));
		return msg;
	}

	/**
	 * 
	 * @methodtype factory
	 * @methodproperties primitive, hook
	 */
	protected Multipart createMultipart(String body) throws MessagingException {
		Multipart mp = new MimeMultipart();
		BodyPart textPart = new MimeBodyPart();
		textPart.setText(body); // sets type to "text/plain"
		mp.addBodyPart(textPart);
		return mp;
	}

	/**
	 * 
	 * @methodproperties primitive, hook
	 */
	protected void doSendEmail(Message msg) throws Exception {
		Transport.send(msg);
	}
	
}
	
	