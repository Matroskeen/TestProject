package com.matroskeen.helpful;

import java.security.SecureRandom;

public class Generator {
	
	static final String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd;
	
	public static String getRandomString(int len) {
		rnd = new SecureRandom();
		
		StringBuilder sb = new StringBuilder(len);
		for(int i = 0; i < len; i++) {
			sb.append(symbols.charAt(rnd.nextInt(symbols.length())));
		}
		return sb.toString();
	}

}
