package hu.unideb.inf.it.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.inf.it.main.Model.User;
import hu.unideb.inf.it.main.service.UserManager;

@Component
public class Authentication {
	@Autowired
	private UserManager um;

	public boolean isSuccessfulAuthentication(String username, String password) {
		if (Validator.isValidInput(username) && Validator.isValidInput(password)) {

			User user = um.getUserByName(username);
			if (user != null) {
				Encryption encryptor = new StrongEncryptor();
				if (encryptor.check(password, user.getPassword())) {
					return true;
				} else {
					return false;
				}
			}

			return false;
		} else {
			return false;
		}

	}

	public UserManager getUm() {
		return um;
	}

	@Autowired
	public void setUm(UserManager um) {
		this.um = um;
	}
}