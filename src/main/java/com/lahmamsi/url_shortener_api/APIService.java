package com.lahmamsi.url_shortener_api;

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
			urlReq.setExpDate(LocalDate.now().plusDays(2));
		if (urlReq.getShortUrl() == null || isLinkExist(urlReq.getShortUrl()))
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
		String encodedLink = genereteHash(orgUrl);
		int urlLength = 5;
		int attemp = 0;
		while(isLinkExist(encodedLink.substring(0,urlLength))) {
			if(urlLength < 20)
				urlLength++;
			else {
				urlLength = 5;
				encodedLink = genereteHash(orgUrl + attemp++);
			}
		}
	
		return encodedLink.substring(0,urlLength);
	}

	private String genereteHash(String orgLink) {
		String hash = DigestUtils.sha256Hex(orgLink);
		return 
				Base62.encode(hash
//								.substring(0, 15)
								.getBytes());
	}

	

}


