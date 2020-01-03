package com.remo.util;

import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static final String defaultPassword = "default";

	private static final String ALGORITHM_NAME = "md5";

	public static String encrypt(String password) {

		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM_NAME);
			return Hex.encode(md.digest(password.getBytes())).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return defaultPassword;
		}
	}

	public static void main(String[] args) {
		System.out.println(encrypt("admin"));
	}

}
