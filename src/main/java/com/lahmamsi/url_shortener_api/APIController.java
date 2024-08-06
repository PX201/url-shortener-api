package com.lahmamsi.url_shortener_api;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class APIController {

	@Autowired
	APIService service;

	@PostMapping("c")
	public ResponseEntity<URLMapping> shorten(URLReq urlreq, HttpServletRequest req) {
		
		URLMapping urlMapping = service.createUrl(urlreq);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(urlMapping);

	}

	@GetMapping("r/{short_link}")
	public ResponseEntity<String> redirectToOriginURL(@PathVariable String shortLink) {
		Optional<URLMapping> urlMappingOp = service.getURL(shortLink);
		if(urlMappingOp.isPresent()) {
			URLMapping urlMapping = urlMappingOp.get();
			if(urlMapping.getOriginalUrl() != null && urlMapping.getExpirationdDate().isBefore(LocalDateTime.now())) {
				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlMapping.getOriginalUrl())).build();
			}
			
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Link has been expired");
		}else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Link Doesn't exist");
	}

}
