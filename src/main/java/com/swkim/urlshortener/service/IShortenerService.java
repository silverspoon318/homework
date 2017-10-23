package com.swkim.urlshortener.service;

import com.swkim.urlshortener.type.ShortedUrlInfo;

public interface IShortenerService {
	public ShortedUrlInfo getShortUrl(String url);
	public ShortedUrlInfo generateUrl(String url) throws Exception;
	public ShortedUrlInfo reverseTinyUrl(String shortUrl);
}
