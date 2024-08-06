package com.lahmamsi.url_shortener_api;

import java.time.LocalDateTime;

public class URLReq {

	private String orgUrl;
	private String customUrl;
	private LocalDateTime expDate;

	public URLReq() {

	}

	public URLReq(String orgUrl, String customUrl, LocalDateTime expDate) {
		this.orgUrl = orgUrl;
		this.customUrl = customUrl;
		this.expDate = expDate;
	}

	public String getOrgUrl() {
		return orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	public String getCustomUrl() {
		return customUrl;
	}

	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}

	public LocalDateTime getExpDate() {
		return expDate;
	}

	public void setExpDate(LocalDateTime expDate) {
		this.expDate = expDate;
	}

}
