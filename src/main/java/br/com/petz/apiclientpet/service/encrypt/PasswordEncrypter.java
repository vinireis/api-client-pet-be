package br.com.petz.apiclientpet.service.encrypt;

import java.security.NoSuchAlgorithmException;

public interface PasswordEncrypter {
	
	String encrypt(String value) throws NoSuchAlgorithmException;
	
}
