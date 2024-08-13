package com.lahmamsi.url_shortener_api;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIService {

	@Autowired
	URLRepo repo;

	public Optional<URLMapping> getURL(String shortLink) {
		return repo.findByShortUrl(shortLink);
	}

	public URLMapping createUrl(URLReq urlReq) {
		if (urlReq.getExpDate() == null || urlReq.getExpDate().isBefore(LocalDate.now()))
			urlReq.setExpDate(LocalDate.now().plusDays(1));
		if (urlReq.getShortUrl() == null || !isLinkExist(urlReq.getShortUrl()))
			urlReq.setShortUrl(generateUrl(urlReq.getOrgUrl()));
		return creatUrlMapping(urlReq);
	}

	private boolean isLinkExist(String shortenLink) {
		return !repo.findByShortUrl(shortenLink).isEmpty();
	}

	private URLMapping creatUrlMapping(URLReq urlReq) {

		URLMapping urlMapping = new URLMapping(urlReq.getOrgUrl(), urlReq.getShortUrl(), LocalDate.now(),
				urlReq.getExpDate());
		return repo.save(urlMapping);
	}

	private String generateUrl(String orgUrl) {
		String shortLink = genereteHash(orgUrl);
		int attemp = 0;
		while (isLinkExist(shortLink)) {
			attemp++;
			orgUrl = orgUrl + attemp;
			shortLink = genereteHash(orgUrl);

		}
		return shortLink;
	}

	private String genereteHash(String orgLink) {
		String hash = DigestUtils.sha256Hex(orgLink);
		return Base62.encode(hash.substring(0, 15).getBytes());
	}

	

}

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
