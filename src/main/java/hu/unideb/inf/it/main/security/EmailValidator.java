package hu.unideb.inf.it.main.security;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;



public class EmailValidator {
	private String email;

	public EmailValidator(String email) {
		this.email = email;
	}


	public boolean validate() {
		if (email == null || email.equals("")) {
			return false;
		} else {
			try {
				InternetAddress emailAddr = new InternetAddress(email);
				emailAddr.validate();
				return true;
			} catch (AddressException ex) {
				return false;
			}
		}
	}

}