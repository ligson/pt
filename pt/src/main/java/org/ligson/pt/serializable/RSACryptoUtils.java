package org.ligson.pt.serializable;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

/***
 * RSA加解密工具
 * 
 * @author ligson
 *
 */
public class RSACryptoUtils {

	/**
	 * RSA最大加密明文大小,只适应与密钥长度为1024
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小,只适应与密钥长度为1024
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	public static byte[] encrypt(PublicKey publicKey, byte[] buffer) {
		try {
			// 对数据加密
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			int inputLen = buffer.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(buffer, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(buffer, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static byte[] decrypt(PrivateKey privateKey, byte[] encryptedData) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] sign(PrivateKey privateKey, byte[] buffer) {
		try {
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(privateKey);
			signature.update(buffer);
			return signature.sign();
		} catch (Exception e) {
		}
		return null;
	}

	public static boolean verify(PublicKey publicKey, byte[] signData, byte[] buffer) {
		try {
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(publicKey);
			signature.update(buffer);
			return signature.verify(signData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}