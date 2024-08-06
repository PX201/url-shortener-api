package com.lahmamsi.url_shortener_api;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface URLRepo extends JpaRepository<URLMapping, Long>{

	void getByShortLink(String shortLink);
	
	Optional<String> getOriginalUrlByShortUrl(String shortUrl);

	Optional<URLMapping> findByShortUrl(String shortLink);
	
}
