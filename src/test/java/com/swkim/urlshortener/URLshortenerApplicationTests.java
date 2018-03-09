
package com.swkim.urlshortener;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.swkim.urlshortener.service.IShortenerService;
import com.swkim.urlshortener.service.impl.UrlShortenerService;
import com.swkim.urlshortener.type.ShortedUrlInfo;
import com.swkim.urlshortener.util.UrlUtil;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { UrlShortenerService.class })
public class URLshortenerApplicationTests {
	@Autowired
	private IShortenerService shortUrlSvc;
	//test

	private AtomicLong counter = new AtomicLong();

	Logger log = Logger.getLogger(URLshortenerApplicationTests.class.getName());
 
	@Test
	public void checkReverseLogic() throws Exception {
		String url = "https://www.twitch.tv/llilkafsdfds";
		ShortedUrlInfo shortedUrlInfo = shortUrlSvc.generateUrl(url);
		log.info("Url generated: " + shortedUrlInfo.getShortUrl());
		String original = shortUrlSvc.reverseTinyUrl(shortedUrlInfo.getShortUrl()).getOriginalUrl();
		log.info("Original Url: " + original);

		Assert.assertEquals(url, original);
	}

	@Test
	public void testGeneration() throws Exception {
		Set<String> urls = new HashSet<>();
		for (int count = 0; count < 100; count++) {

			final long nextNumber = getNextNumber();
			String generatedSeq = UrlUtil.convertAndGetBase62Code(nextNumber);

			urls.add(generatedSeq);
		}
		log.info("Urls generated : " + urls);
		Assert.assertEquals(100, urls.size());
	}

	private long getNextNumber() throws Exception {
		long counterValue = counter.incrementAndGet();

		if (UrlUtil.maxCount() < counterValue) {
			if (UrlUtil.maxCount() < counterValue)
				new Exception("Short URL Maximum count exceeded.");
			log.info("Short URL Maximum count exceeded.");
		}

		long systemTime = Long.valueOf("" + counterValue);

		return systemTime;
	}
}
