package com.swkim.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swkim.urlshortener.annotation.CheckUrl;
import com.swkim.urlshortener.service.IShortenerService;
import com.swkim.urlshortener.type.JsonResult;
import com.swkim.urlshortener.type.ShortedUrlInfo;
import com.swkim.urlshortener.util.UrlUtil;

@RestController
@RequestMapping("/api")
@CheckUrl
public class ApiShortController {
	@Autowired
	private IShortenerService shortUrlSvc;
	
	@RequestMapping(value = "/shorten", method = { RequestMethod.POST })
	public JsonResult actionPostShortUrl(@RequestParam(value="url", required=true) String url) throws Exception {
		String decodedUrl = UrlUtil.decodeUrl(url);
		ShortedUrlInfo shortedUrlInfo = shortUrlSvc.getShortUrl(decodedUrl);
		if (shortedUrlInfo == null) {
			shortedUrlInfo = shortUrlSvc.generateUrl(decodedUrl);
		}
		
		return JsonResult.success(shortedUrlInfo);
	}
}
