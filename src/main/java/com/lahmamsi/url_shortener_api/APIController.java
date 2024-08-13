package com.lahmamsi.url_shortener_api;

import java.net.URI;


import java.time.LocalDate;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.lahmamsi.url_shortener_api.URLUtills.addBaseUrlToShortenUrl;

@Controller
@RequestMapping("/")
public class APIController {

	@Autowired
	APIService service;

	@PostMapping("")
	public ResponseEntity<URLMapping> shorten(@RequestBody URLReq urlreq, HttpServletRequest req) {
		
		URLMapping urlMapping = service.createUrl(urlreq);
		
		return ResponseEntity.status(HttpStatus.CREATED)
								.body(addBaseUrlToShortenUrl(urlMapping, req));

	}

	@GetMapping("r/{shortLink}")
	public ResponseEntity<String> redirectToOriginURL(@PathVariable String shortLink) {
		Optional<URLMapping> urlMappingOp = service.getURL(shortLink);
		if(urlMappingOp.isPresent()) {
			URLMapping urlMapping = urlMappingOp.get();
			if(urlMapping.getOriginUrl() != null && urlMapping.getExpirationdDate().isAfter(LocalDate.now())) {
				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlMapping.getOriginUrl())).build();
			}
			
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Link has been expired");
		}else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Link Doesn't exist");
	}

}
