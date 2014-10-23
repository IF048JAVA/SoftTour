package com.softserveinc.softtour.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
/**
 * @author Andriy
 * Encodes a password 
 */
public class PasswordEncoder {
	private static final String ENCODING_ALGORITHM = "SHA-256";

	/**
	 * Encodes a specified password 
	 * @param password that will be encoded
	 * @return an encoded a password 
	 */
	public static String encode(String password) {
		try {
			byte[] bytePassword = password.getBytes();
			MessageDigest md = MessageDigest.getInstance(ENCODING_ALGORITHM);
			byte[] byteHashPassword = md.digest(bytePassword);
			
			String hashedPassword = Hex.encodeHexString(byteHashPassword);
			
			return hashedPassword;
		} catch (NoSuchAlgorithmException e) {
			// TODO Add logging here
			e.printStackTrace();
		}
		return null;
	}
}
