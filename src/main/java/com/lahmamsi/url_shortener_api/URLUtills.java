package com.lahmamsi.url_shortener_api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class URLUtills {

	public static URLMapping addBaseUrlToShortenUrl(URLMapping urlMapping, HttpServletRequest req) {
		String ShortenUrlWithBaseUrl = ServletUriComponentsBuilder.fromRequestUri(req).path("r/{id}")
				.buildAndExpand(urlMapping.getShortUrl()).toString();
		urlMapping.setShortUrl(ShortenUrlWithBaseUrl);
		return urlMapping;
	}

	public static URLMapping removeBaseUrlFromShortUrl(URLMapping urlMapping) {

		String shortLink = urlMapping.getShortUrl();
		String shortLinkWithoutBaseUrl = shortLink.substring(shortLink.lastIndexOf('/'));
		urlMapping.setShortUrl(shortLinkWithoutBaseUrl);
		return urlMapping;
	}
}
