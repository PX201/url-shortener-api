package com.lahmamsi.url_shortener_api;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class URLReq {

	@NotNull
	private String orgUrl;
	@Size(max = 20, message = "The short link size can't be exceed 20 characher")
	private String shortUrl;
	private LocalDate expDate;

	public URLReq() {

	}

	public URLReq(String orgUrl, String shortUrl, LocalDate expDate) {
		this.orgUrl = orgUrl;
		this.shortUrl = shortUrl;
		this.expDate = expDate;
	}

	public String getOrgUrl() {
		return orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public LocalDate getExpDate() {
		return expDate;
	}

	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}

}
