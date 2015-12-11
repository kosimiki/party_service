package hu.unideb.inf.it.main.security;

public interface Encryption {

	public String encrypt(String toEncrypt);

	
	public boolean check(String inputPassword, String actualPassword); 
}
