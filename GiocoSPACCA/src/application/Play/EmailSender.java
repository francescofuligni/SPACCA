package application.Play;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType; 

public class EmailSender {
	
	public static void sendMail(String to, String messageText) {
		// invia l'email all'indirizzo selezionato
		// (riceve il messaggio in input)
		
		Properties properties = new Properties();
		// protocollo smtp con tls
		properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
		final String senderEmail = "notifica.spacca.the.game@gmail.com";	// indirizzo da cui viene inviata l'email
		final String senderPsw = "gtgx pnqy mjgm uaea";						// application password creata da gmail
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail,senderPsw);
			}
		});
		try {
			Message message = prepareMessage (session, senderEmail, to, messageText);
			Transport.send(message);
		} catch (MessagingException e) {
			Alert exceptionAlert = new Alert(AlertType.ERROR);
			exceptionAlert.setTitle("ERRORE");
			exceptionAlert.setHeaderText("Errore nell'invio dell'email");
			exceptionAlert.setContentText(e.getClass().getSimpleName());
			exceptionAlert.showAndWait();
		}
	}
	
	private static Message prepareMessage(Session session, String senderEmail, String to, String messageText) throws MessagingException {
		// costruisce il messaggio email
		Message message=new MimeMessage(session);
		message.setFrom(new InternetAddress(senderEmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Risultati Partita SPACCA: " + InsertCodeController.code);
		message.setText(messageText);
		return message;
	}
}
