package com.softserveinc.softtour.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.softtour.parsers.TrainParser;
/**
 * @author Andriy
 * Encodes a password 
 */
public class PasswordEncoder {
	private static final String ENCODING_ALGORITHM = "SHA-256";
	private static final Logger LOG = LoggerFactory.getLogger(TrainParser.class);
	
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
			LOG.error("Error of encoding algorithm. "
				+ "Encoding algorithm " + ENCODING_ALGORITHM + " is not supported." );
		}			
		return null;
	}
}
