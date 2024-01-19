package application.Play;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class EmailSender {
	
	public static void sendMail(String to) throws Exception {
		Properties properties = new Properties();
		properties.put("mail.sntp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put ("mail. smtp.host", "smtp.gmail.com");
		properties.put ("mail.smtp.port", "587");
		
		String senderEmail= "notifica.spacca.the.game@gmail.com";
		String senderPsw="SPACCA.carmelino99@";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail,senderPsw);
			}
		});
		
		Message message = prepareMessage (session, senderEmail, to);
		
		Transport.send(message);
	}
	
	private static Message prepareMessage(Session session, String senderEmail, String to) {
		
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("PROVA MESSAGGIO");
			message.setText("PROVA PROVA PROVA");
			return message;
		}catch(Exception e) {
			Logger.getLogger(EmailSender.class.getName()). log(Level.SEVERE, null, e);
		}
		
		return null;
		
	}
	
}
