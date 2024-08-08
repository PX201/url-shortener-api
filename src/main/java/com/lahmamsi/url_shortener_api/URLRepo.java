package com.lahmamsi.url_shortener_api;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface URLRepo extends JpaRepository<URLMapping, Long>{

	Optional<URLMapping> findByShortUrl(String shortLink);
	
}
