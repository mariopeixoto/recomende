package br.recomende.model.recommending;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator {
	
	private String email;
	private String password;
	
	public SMTPAuthenticator(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(email, password);
	}
}