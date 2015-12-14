package hu.unideb.inf.it.main.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class StrongEncryptor implements Encryption {
//	private StrongPasswordEncryptor passwordEncryptor;
	


	public StrongEncryptor() {
//		passwordEncryptor = new StrongPasswordEncryptor();
	}

	@Override
	public String encrypt(String toEncrypt) {
//		return passwordEncryptor.encryptPassword(toEncrypt);
		return DigestUtils.md5Hex(toEncrypt);
	}

	@Override
	public boolean check(String input, String actualPwd) {
		if(actualPwd.equals(DigestUtils.md5Hex(input))){
			return true;
		}else{
			return false;
		}
		
//		if (passwordEncryptor.checkPassword(input, actualPwd)) {
//			return true;
//		} else {
//			return false;
//		}
	}

}
