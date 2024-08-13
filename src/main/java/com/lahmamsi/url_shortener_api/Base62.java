package com.lahmamsi.url_shortener_api;

import java.math.BigInteger;

class Base62 {
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int BASE = ALPHABET.length();

	public static String encode(byte[] input) {
		StringBuilder result = new StringBuilder();
		BigInteger value = new BigInteger(1, input);

		while (value.compareTo(BigInteger.ZERO) > 0) {
			BigInteger[] divmod = value.divideAndRemainder(BigInteger.valueOf(BASE));
			value = divmod[0];
			result.append(ALPHABET.charAt(divmod[1].intValue()));
		}

		return result.reverse().toString();
	}
}
