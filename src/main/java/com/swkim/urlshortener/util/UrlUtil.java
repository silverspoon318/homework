package com.swkim.urlshortener.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlUtil {
	private static String charsetStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static int base = charsetStr.length();
	private static int shortUrlMaxLength = 8;
	
	public static String convertAndGetBase62Code(long num) {
		StringBuffer sb = new StringBuffer("");

		while (num > 0) {
			int remainder = (int) (num % base);
			sb.append(charsetStr.charAt(remainder));
			num = num / base;
		}

		return sb.toString();
	}
	
	public static long convertToBase10(String tinyUrlCode) {
		long nBase10 = 0;
		char[] chars = new StringBuilder(tinyUrlCode).toString().toCharArray();

		for (int i = chars.length - 1; i >= 0; i--) {
			int index = charsetStr.indexOf(chars[i]);

			nBase10 += index * (long) Math.pow(base, i);
		}

		return nBase10;
	}
	
	public static long maxCount() {
		return (long)Math.pow(base, shortUrlMaxLength); 
	}
	
	public static int getShortUrlMaxLength() {
		return shortUrlMaxLength;
	}
	
	public static String decodeUrl(String url) throws UnsupportedEncodingException {
		String encodedUrl = URLDecoder.decode(url, "UTF-8");
		
		return encodedUrl;
	}
}
