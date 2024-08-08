package com.lahmamsi.url_shortener_api;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "url_mapping")
public class URLMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@Column
	@NotNull
	private String originalUrl;

	@Column(unique = true)
	private String shortUrl;

	@Column
	private LocalDate createdAt;

	@Column(name = "expiration_date")
	@NotNull
	private LocalDate expirationdDate;

	@Column
	private long clickCount;

	public URLMapping() {

	}

	public URLMapping(String originalUrl, String shortUrl, LocalDate createdAt, LocalDate expirationdDate) {
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.createdAt = createdAt;
		this.expirationdDate = expirationdDate;
	}

	public URLMapping(long id, String originalUrl, String shortUrl, LocalDate createdAt,
			LocalDate expirationdDate, long clickCount) {
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getExpirationdDate() {
		return expirationdDate;
	}

	public void setExpirationdDate(LocalDate expirationdDate) {
		this.expirationdDate = expirationdDate;
	}

	public long getClickCount() {
		return clickCount;
	}

	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}

}
