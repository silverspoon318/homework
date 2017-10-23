package com.swkim.urlshortener;

import java.util.concurrent.atomic.AtomicLong;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.swkim.urlshortener.service.impl.UrlShortenerService;
import com.swkim.urlshortener.util.UrlUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { UrlShortenerService.class })
public class URLshortnerMaxCountTest {

	private AtomicLong counter = new AtomicLong(UrlUtil.maxCount());

	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Test
	public void testException() throws Exception {
		thrown.expect(Exception.class);
		thrown.expectMessage("Short URL Maximum count exceeded.");
		getNextNumber();
		
	}

	public long getNextNumber() throws Exception {
		long counterValue = counter.incrementAndGet();
		if (UrlUtil.maxCount() < counterValue) {
			throw new Exception("Short URL Maximum count exceeded.");
		}

		long systemTime = Long.valueOf("" + counterValue);

		return systemTime;
	}
}
