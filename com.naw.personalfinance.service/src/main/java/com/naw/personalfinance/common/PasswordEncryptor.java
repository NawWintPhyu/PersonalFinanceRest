package com.naw.personalfinance.common;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
	public static String hashPassword(String password) {
		String salt = BCrypt.gensalt();
		String hashedPassword = BCrypt.hashpw(password, salt);
		return hashedPassword;

	}

}
