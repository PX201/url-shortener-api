package com.lahmamsi.url_shortener_api;

import java.time.LocalDate;

public class URLReq {

	private String orgUrl;
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
