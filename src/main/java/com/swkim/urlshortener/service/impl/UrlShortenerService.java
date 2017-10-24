package com.swkim.urlshortener.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.swkim.urlshortener.exception.ShortenerException;
import com.swkim.urlshortener.service.IShortenerService;
import com.swkim.urlshortener.type.ShortedUrlInfo;
import com.swkim.urlshortener.util.UrlUtil;

@Service
public class UrlShortenerService implements IShortenerService {

	private static AtomicLong counter = new AtomicLong();
	private static Map<Long, ShortedUrlInfo> store = new ConcurrentHashMap<>();

	public ShortedUrlInfo getShortUrl(String url) {
		for (Long key : store.keySet()) {
			if (store.get(key).getOriginalUrl().equals(url)) {
				return store.get(key);
			}
		}
		return null;
	}
	
	public ShortedUrlInfo generateUrl(String url) throws Exception {
		ShortedUrlInfo shortUrlInfo = new ShortedUrlInfo();
		
		final long nextNumber = getNextNumber();
		String shortUrl = UrlUtil.convertAndGetBase62Code(nextNumber);
		shortUrlInfo.setOriginalUrl(url);
		shortUrlInfo.setShortUrl(shortUrl);
		store.put(nextNumber, shortUrlInfo);

		return shortUrlInfo;

	}

	public ShortedUrlInfo reverseTinyUrl(String shortUrl) {
		long shortUrlId = UrlUtil.convertToBase10(shortUrl);
		if (shortUrlId > UrlUtil.getShortUrlMaxLength())
			return null;

		return store.get(shortUrlId);
	}

	private long getNextNumber() throws Exception {
		long counterValue = counter.incrementAndGet();

		if (UrlUtil.maxCount() < counterValue)
			throw new ShortenerException().setErrorCode(-3).setErrorMessage("Short URL Maximum count exceeded.");

		return counterValue;
	}
}
