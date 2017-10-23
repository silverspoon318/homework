package com.swkim.urlshortener.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class AppConfig {
	private int shortUrlMAXLength;
	private String charsetStr;

	public String getCharsetStr() {
		return charsetStr;
	}

	public void setCharsetStr(String charsetStr) {
		this.charsetStr = charsetStr;
	}

	public int getShortUrlMAXLength() {
		return shortUrlMAXLength;
	}

	public void setShortUrlMAXLength(int shortUrlMAXLength) {
		this.shortUrlMAXLength = shortUrlMAXLength;
	}
}
