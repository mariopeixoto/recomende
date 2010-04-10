package br.recomende.model.recommending;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;

@Component
@Scope(SpringScope.APPLICATION)
public class RecommedationSender {
	
	private static final String from = "recomende.recommendation@gmail.com";
	private static final String password = "uosn832681";
	private static final String subject = "Recommendation";
	
    public void send(String to, String recommendations) throws AddressException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        
		System.getSecurityManager();

        Authenticator auth = new SMTPAuthenticator(from, password);
        Session session = Session.getInstance(props, auth);
        
        MimeMessage msg = new MimeMessage(session);
        msg.setText(recommendations);
        msg.setSubject(subject);
        msg.setFrom(new InternetAddress(from));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setHeader("Content-Type", "text/html; charset=UTF-8");
        msg.setSentDate(new Date());
        Transport.send(msg);
    }
}
