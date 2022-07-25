package org.sid.catservice.util;

//import com.sun.mail.smtp.SMTPTransport;
//import org.apache.geronimo.javamail.transport.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class MailUtil {

	/*
	 * private static final String SMTP_SERVER = "smtp.topnet.tn"; private static
	 * final String SMTP_PORT = "587"; private static final String USERNAME = "";
	 * private static final String PASSWORD = "";
	 */
	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static final String SMTP_PORT = "587";
	private static final String USERNAME = "ayoub.abk@gmail.com";
	private static final String PASSWORD = "01366453";

	private static final String EMAIL_FROM = "ayoub.abk@gmail.com";

	private static final String EMAIL_VALIDATE_SUBJECT = "Validation compte";

	/*public static void SendMailValidation(Client client, String TypeMail)
			throws MessagingException, SendFailedException, AddressException, NoSuchProviderException {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", SMTP_SERVER);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", SMTP_PORT);
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, null);
		Message msg = new MimeMessage(session);

		try {

			// from
			msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(client.getCltMail(), false));

			// subject
			msg.setSubject(EMAIL_VALIDATE_SUBJECT);

			// content
			String contentMessage = "";

			if (TypeMail.equals("validat")) {
				contentMessage = "Votre compte est créer à la plateforme (Fédélité)." + "votre code de validation et :"
						+ client.getCle();
			} else {
				contentMessage = "Votre code de confirmation pour changer password et :" + client.getCle();

			}

			System.out.println("* * Method SendMailValidation 0 * *");

			msg.setContent(contentMessage, "text/html");
			System.out.println("* * Method SendMailValidation 0.5 * *");
			msg.setSentDate(new Date());
			System.out.println("* * Method SendMailValidation 0.8 * *");
			// Get SMTPTransport
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			System.out.println("* * Method SendMailValidation 1 * *");

			// connect
			t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			System.out.println("* * Method SendMailValidation 2 * *");
			// send
			t.sendMessage(msg, msg.getAllRecipients());
			System.out.println("* * Method SendMailValidation 3 * *");
			System.out.println("Response: " + t.getLastServerResponse());

			t.close();

		} catch (MessagingException ex) {
			throw ex;

		}
	}
*/
	/*public static void SendMailValidation(Administrateur utilisateur, String TypeMail)
			throws MessagingException, SendFailedException, AddressException, NoSuchProviderException {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", SMTP_SERVER);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", SMTP_PORT);
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, null);
		Message msg = new MimeMessage(session);

		try {

// from
			msg.setFrom(new InternetAddress(EMAIL_FROM));

// to
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utilisateur.getAdminMail(), false));

// subject
			msg.setSubject(EMAIL_VALIDATE_SUBJECT);

// content
			String contentMessage = "";

			if (TypeMail.equals("validat")) {
				contentMessage = "Votre compte est créer à la plateforme (Fédélité)." + "votre code de validation et :"
						+ utilisateur.getAdminCle();
			} else {
				contentMessage = "Votre code de confirmation pour changer password et :" + utilisateur.getAdminCle();

			}

			System.out.println("* * Method SendMailValidation 0 * *");

			msg.setContent(contentMessage, "text/html");
			System.out.println("* * Method SendMailValidation 0.5 * *");
			msg.setSentDate(new Date());
			System.out.println("* * Method SendMailValidation 0.8 * *");
// Get SMTPTransport
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			System.out.println("* * Method SendMailValidation 1 * *");

// connect
			t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			System.out.println("* * Method SendMailValidation 2 * *");
// send
			t.sendMessage(msg, msg.getAllRecipients());
			System.out.println("* * Method SendMailValidation 3 * *");
			System.out.println("Response: " + t.getLastServerResponse());

			t.close();

		} catch (MessagingException ex) {
			throw ex;

		}
	}
	*/
}
