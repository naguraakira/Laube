package site.laube.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import site.laube.exception.LaubeException;

/*
 * Copyright (c) 2016, Ryuta Miki All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public final class LaubeUtility implements Serializable {

	/**
	 * To manage the serial version ID.<br>
	 */
	private static final long serialVersionUID = -1729825188444472402L;

	/**
	 * Conversion to a variable item → database item<br>
	 * Since the database is a snake case notation, to convert the variable of camel case notation to snake case notation.<br>
	 * @param targetStr Before conversion string
	 * @return Post-conversion string
	 */
	public static final String camelToSnake(String targetStr) {

		String convertedStr = targetStr
			.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
			.replaceAll("([a-z])([A-Z])", "$1_$2");
		return convertedStr.toLowerCase();
	}

	/**
	 * Converted to the database entry → variable item<br>
	 * Because the variable is a camel case notation, and then convert the database entry of snake case notation to camel case notation.<br>
	 * @param targetStr Before conversion string
	 * @return Post-conversion string
	 */
	public static final String snakeToCamel(String targetStr) {

		Pattern p = Pattern.compile("_([a-z])");
		Matcher m = p.matcher(targetStr.toLowerCase());

		StringBuffer sb = new StringBuffer(targetStr.length());

		while (m.find()) {
			m.appendReplacement(sb, m.group(1).toUpperCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * To encrypt.<br>
	 * Encryption method: the method of encryption with password-based encryption method (PKCS # 5) <br>
	 * @param text Before encryption of string
	 * @return Encrypted string
	 * @throws LaubeException
	 */
	public static final String doEncryption(String text) throws LaubeException {

		try {

			PBEKeySpec              pBEKeySpec;
			PBEParameterSpec        pBEParameterSpec;
			SecretKeyFactory        secretKeyFactory;
			SecretKey               secretKey;
			Cipher                  cipher;

			char[] password = null;

			if (StringUtils.isNotEmpty(SpecifiedValue.CipherPassword)) {
				password = SpecifiedValue.CipherPassword.toCharArray();
			}else{
				throw new LaubeException("Password is empty.");
			}

			// Salt value
			final byte[] salt = {(byte)0xc7, (byte)0x73, (byte)0x21,(byte)0x8c, (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99};

			// Iteration count value
			final int count = 2048;

			// (1)PBE Parameter generation
			pBEParameterSpec = new PBEParameterSpec(salt, count);

			// (2)Generating a secret key from a password
			pBEKeySpec = new PBEKeySpec(password);

			Arrays.fill(password, (char)0x00); // overrides remove the security information.

			secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			secretKey = secretKeyFactory.generateSecret(pBEKeySpec);

			// (3)Encryption preparation
			cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, pBEParameterSpec);

			// Clear text
			byte[] cleartext = text.getBytes();

			// (4)encryption
			byte[] ciphertext = cipher.doFinal(cleartext);

			// CODEBASE-64 Encode
			byte[] encodedBytes = Base64.encodeBase64(ciphertext);
			return new String(encodedBytes, "UTF-8");

		} catch (NoSuchAlgorithmException e) {
			throw new LaubeException(e);

		} catch (NoSuchPaddingException e) {
			throw new LaubeException(e);

		} catch (InvalidKeyException e) {
			throw new LaubeException(e);

		} catch (InvalidAlgorithmParameterException e) {
			throw new LaubeException(e);

		} catch (InvalidKeySpecException e) {
			throw new LaubeException(e);

		} catch (IllegalBlockSizeException e) {
			throw new LaubeException(e);

		} catch (BadPaddingException e) {
			throw new LaubeException(e);

		} catch (UnsupportedEncodingException e) {
			throw new LaubeException(e);
		}
	}

	/**
	 * Do the composite.<br>
	 * Encryption method of encryption with password-based encryption method method (PKCS # 5)
	 * @param text Before the composite string
	 * @return Composite string
	 * @throws LaubeException
	 */
	public static final String doDecryption(String text) throws LaubeException {

		try {

			char[] password = null;

			if (StringUtils.isNotEmpty(SpecifiedValue.CipherPassword)) {
				password = SpecifiedValue.CipherPassword.toCharArray();
			}else{
				throw new LaubeException("Password is empty.");
			}

			// Salt value
			byte[] salt = {(byte)0xc7, (byte)0x73, (byte)0x21,(byte)0x8c, (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99};

			// Iteration count value
			int count = 2048;

			// CODEBASE-64 Decode
			byte[] decryptionText = Base64.decodeBase64(text);

			// (5)to generate a new PBE parameters
			PBEParameterSpec pbeParamSpecDec = new PBEParameterSpec(salt, count);

			PBEKeySpec pbeKeySpecDec = new PBEKeySpec(password);
			Arrays.fill(password, (char)0x00); // overrides remove the security information.

			SecretKeyFactory keyFacDec = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKeyDec = keyFacDec.generateSecret(pbeKeySpecDec);

			// (7)Decoding preparation
			Cipher cDec = Cipher.getInstance("PBEWithMD5AndDES");
			cDec.init(Cipher.DECRYPT_MODE, pbeKeyDec, pbeParamSpecDec);

			// (8)Decoding
			byte[] output = cDec.doFinal(decryptionText);
			return new String(output, "UTF-8");

		} catch (NoSuchAlgorithmException e) {
			throw new LaubeException(e);

		} catch (NoSuchPaddingException e) {
			throw new LaubeException(e);

		} catch (InvalidKeyException e) {
			throw new LaubeException(e);

		} catch (InvalidAlgorithmParameterException e) {
			throw new LaubeException(e);

		} catch (InvalidKeySpecException e) {
			throw new LaubeException(e);

		} catch (IllegalBlockSizeException e) {
			throw new LaubeException(e);

		} catch (BadPaddingException e) {
			throw new LaubeException(e);

		} catch (UnsupportedEncodingException e) {
			throw new LaubeException(e);
		}
	}

	/**
	 * Secret key<br>
	 */
	private static PrivateKey privateKey;

	/**
	 * Public keybr>
	 */
	private static PublicKey publicKey;

	/**
	 * It gets the key pair.<br>
	 * @throws LaubeException
	 */
	public static final void getKeyPair() throws LaubeException {

		try {
			String ksType = "JKS";
			String keyStoreFile = "sample";
			String keyStorePass = "sample";
			String alias = "sample";
			String privateKeyPass = "sample";

			KeyStore keyStore;
			keyStore = KeyStore.getInstance(ksType);
			keyStore.load(new FileInputStream(keyStoreFile), keyStorePass.toCharArray());
			privateKey = (PrivateKey) keyStore.getKey(alias,privateKeyPass.toCharArray());

			X509Certificate certificate = (X509Certificate) keyStore.getCertificate(alias);
			publicKey = certificate.getPublicKey();

		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException e) {
			throw new LaubeException(e);
		}
	}

	/**
	 * To encrypt.<br>
	 * Cryptography: public key cryptography
	 * @param text Before encryption of string
	 * @return Encrypted string
	 * @throws LaubeException
	 */
	public static final byte[] encrypt(String data) throws LaubeException {

		Cipher cipher = null;

		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			return cipher.doFinal(data.getBytes());

		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new LaubeException(e);
		}
	}

	/**
	 * Do the composite.<br>
	 * Encryption method of encryption with password-based encryption method method (PKCS # 5)
	 * @param text Before the composite string
	 * @return omposite string
	 * @throws LaubeException
	 */
	public static final byte[] decrypt(byte[] data) throws LaubeException {

		Cipher cipher;

		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			return cipher.doFinal(data);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new LaubeException(e);

		} catch (InvalidKeyException e) {
			throw new LaubeException(e);

		} catch (IllegalBlockSizeException e) {
			throw new LaubeException(e);

		} catch (BadPaddingException e) {
			throw new LaubeException(e);
		}
	}

	/**
	 * Do not set check.<br>
	 * Wrapper class for hiding the "org.apache.commons.lang3.builder.ToStringBuilder", which is an external class<br>
	 * @param s Value to validate
	 * @return result
	 */
	public static final boolean isEmpty(String s) {
		return StringUtils.isEmpty(s);
	}

	/**
	 * Do not set check.<br>
	 * Wrapper class for hiding the "org.apache.commons.lang3.builder.ToStringBuilder", which is an external class<br>
	 * @param s Value to validate
	 * @return result
	 */
	public static final boolean isBlank(String s) {
		return StringUtils.isBlank(s);
	}

	/**
	 * Make the configured check.<br>
	 * Wrapper class for hiding the "org.apache.commons.lang3.builder.ToStringBuilder", which is an external class<br>
	 * @param s Value to validate
	 * @return result
	 */
	public static final boolean isNotEmpty(String s) {
		return StringUtils.isNotEmpty(s);
	}

	/**
	 * It performs a number check.<br>
	 * Wrapper class for hiding the "org.apache.commons.lang3.builder.ToStringBuilder", which is an external class<br>
	 * @param s Value to validate
	 * @return result
	 */
	public static final boolean isDigits(String s) {
		return NumberUtils.isDigits(s);
	}

	/**
	 * It converts a date string to date type.<br>
	 * Wrapper class for hiding the "org.apache.commons.lang3.builder.ToStringBuilder", which is an external class<br>
	 * @param strDate yyyy-MM-dd /yyyy/MM/dd / yyyy/MM/dd HH:mm:ss One of a string of the form
	 * @return After conversion of the value
	 */
	public static final Date parseDate(String strDate) throws LaubeException {

		try {
			return DateUtils.parseDate(strDate, SpecifiedValue.DateFormat);

		} catch (ParseException e) {
			throw new LaubeException(e);
		}
	}

	/**
	 * It converts the contents of type String.<br>
	 * Wrapper class for hiding the "org.apache.commons.lang3.builder.ToStringBuilder", which is an external class<br>
	 * @param o object
	 * @return After conversion of the value
	 */
	public static final String reflectionToString(Object o) {
		return ToStringBuilder.reflectionToString(o, ToStringStyle.DEFAULT_STYLE).toString();
	}
}
