package com.lahmamsi.url_shortener_api;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "url_mapping")
public class URLMapping {

	@Id
	@Column
	private long id;

	@Column
	@NotNull
	private String originalUrl;

	@Column(unique = true)
	private String shortUrl;

	@Column
	private LocalDateTime createdAt;

	@Column
	private LocalDateTime expirationdDate;

	@Column
	private long clickCount;

	public URLMapping() {

	}

	public URLMapping(String originalUrl, String shortUrl, LocalDateTime createdAt, LocalDateTime expirationdDate) {
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.createdAt = createdAt;
		this.expirationdDate = expirationdDate;
	}

	public URLMapping(long id, String originalUrl, String shortUrl, LocalDateTime createdAt,
			LocalDateTime expirationdDate, long clickCount) {
		super();
		this.id = id;
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.createdAt = createdAt;
		this.expirationdDate = expirationdDate;
		this.clickCount = clickCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpirationdDate() {
		return expirationdDate;
	}

	public void setExpirationdDate(LocalDateTime expirationdDate) {
		this.expirationdDate = expirationdDate;
	}

	public long getClickCount() {
		return clickCount;
	}

	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}

}
