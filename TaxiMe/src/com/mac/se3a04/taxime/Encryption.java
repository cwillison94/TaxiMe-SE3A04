package com.mac.se3a04.taxime;

import android.annotation.SuppressLint;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Encryption {

	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = new byte[] { 'K', 'i', 'N', 'G', 'K', 'H', 'e', 'd',
			'R', 'i' ,'a','B', 'c', 'D', 'D', 'z'};

	@SuppressLint("TrulyRandom")
	public static String encrypt(String data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(data.getBytes());
		byte[] encryptedValue = Base64.encodeBase64(encVal);
		return new String(encryptedValue);
	}

	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.decodeBase64(encryptedData.getBytes());
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}

}
