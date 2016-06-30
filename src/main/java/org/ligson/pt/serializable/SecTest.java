package org.ligson.pt.serializable;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;

public class SecTest {
	static String priKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANO+fbDdGUhl8EwhCGUwRw2929/LP/AwwvCZz8xT8QHI693IpPZXo2CaJSyapEetLI3XhZgPg8dJpaOUDLWTwgWUrXk1BW4Okwd4HfSH1OOQGKUllXL/cdAwCpTWGC2VwZe2byiR25ddzMBKw21qwZH0dYRF+MaCxFODJDY+ysLjAgMBAAECgYEAqzjXpKQ1ZKDh3n27ry+J1WfYm7BcZJrZl/uY2A5szdeuO2qbUoRtnZkoRFhMTZWQeKZ15g9trUI1igAXAuGXurgvP+nRDXUQSCj8luPvuSX85ExmXy8xKILirgrONIYnsH59sKTFc7i3VZog5y++2cdRLxBBZfiyfDJOpQK2BakCQQD40f2BkzivyOLwlotR8+3/RB3h9kzcFHUoK+00FQvDH7RCLXQOtkqtzm9VQngGDLN7vS8VrO2aMX+m6RoOJ3PVAkEA2dqfdQKdS9Nlqh4ZQ/2tULBOi8/6qNzaKxMWREqtXSZrPh+iSfNOCgvIVObV2Jzh4ZV32erT2bAo5mK7/uMP1wJAM9XjXxwhghVymbZzHcaYfUZeh1V9W5fzdEP6bZA43BR9xA+eDvaRBjZsYTGwuWnp9kxKQ9S7xxSqyEXlhK9ZZQJAORhrIieFQhItWuB4jrE1cyhB7bKcZxU6uH9QHqXTuc4P3UA9MFRr54YfAuFcumCjcpPRzXMWAr+AeKD31F1+EQJAENJnG0cgDTa1w7AQRlEusXqwVe0KaBTIar9rEqy/rWGkUhvULQigTF3sLMSw0CfK+IM9LfZ2p1q/8zexQC1fBw==";
	static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTvn2w3RlIZfBMIQhlMEcNvdvfyz/wMMLwmc/MU/EByOvdyKT2V6NgmiUsmqRHrSyN14WYD4PHSaWjlAy1k8IFlK15NQVuDpMHeB30h9TjkBilJZVy/3HQMAqU1hgtlcGXtm8okduXXczASsNtasGR9HWERfjGgsRTgyQ2PsrC4wIDAQAB";

	public static void main(String[] args) throws Exception {
		String data = UUID.randomUUID().toString();
		System.out.println(data);

		PublicKey publicKey = KeyUtils.decodePublicKey(pubKey);
		PrivateKey privateKey = KeyUtils.decodePrivateKey(priKey);
		// byte[] buffer = RSACryptoUtils.encrypt(publicKey,
		// data.getBytes("UTF-8"));

		// byte[] buffer2 = RSACryptoUtils.decrypt(privateKey, buffer);

		// System.out.println(new String(buffer2,"UTF-8"));
		String data2 = TokenUtils.encode(data, pubKey);
		System.out.println(data2);

		String data3 = TokenUtils.decode(data2, priKey);
		System.out.println(data3);

	}
}
