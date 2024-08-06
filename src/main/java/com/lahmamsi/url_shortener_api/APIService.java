package com.lahmamsi.url_shortener_api;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIService {
	
	@Autowired
	URLRepo repo;

	public Optional<URLMapping> getURL(String shortLink) {
		return repo.findByShortUrl(shortLink);
	}

	private String getOriginURL(String shortLink) {
		return repo.getOriginalUrlByShortUrl(shortLink).orElse(null);
	}

	public URLMapping createUrl(URLReq urlReq) {
		if(urlReq.getExpDate() == null || urlReq.getExpDate().isBefore(LocalDateTime.now()))
			urlReq.setExpDate(LocalDateTime.now().plusHours(24));
		if(urlReq.getCustomUrl() == null || !isLinkExist(urlReq.getCustomUrl()))
			urlReq.setCustomUrl(generateUrl(urlReq.getOrgUrl()));
			return creatUrlMapping(urlReq);
	}

	
	private String generateUrl(String orgLink) {
		String shortenLink = "";
		do {
			// logic to create a short url
			
		}
		while(isLinkExist(shortenLink));
		return shortenLink;
	}

	private boolean isLinkExist(String shortenLink) {
		return !repo.findByShortUrl(shortenLink).isEmpty();
	}

	private URLMapping creatUrlMapping(URLReq urlReq) {

		URLMapping urlMapping = 
				new URLMapping( urlReq.getOrgUrl(), urlReq.getCustomUrl(), LocalDateTime.now(), urlReq.getExpDate());
		return repo.save(urlMapping);
	}



}
