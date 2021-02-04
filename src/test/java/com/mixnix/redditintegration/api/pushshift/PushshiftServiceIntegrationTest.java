package com.mixnix.redditintegration.api.pushshift;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mixnix.redditintegration.api.pushshift.domain.UrlResponseDTO;
import com.mixnix.redditintegration.api.pushshift.service.PushshiftServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PushshiftServiceIntegrationTest {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PushshiftServiceImpl pushshiftService;

	@Autowired
	private ObjectMapper objectMapper;

	private MockRestServiceServer server;

	@Before
	public void init() {
		server = MockRestServiceServer.createServer(restTemplate);


	}

	@Test
	public void shouldReturnResponseWithProperData() throws Exception {
		String detailsString =
				objectMapper.writeValueAsString(new RedditDataDTO(new ArrayList<RedditSubmissionDTO>(
						Arrays.asList(
								new RedditSubmissionDTO(1611234261L, "https://google.com"),
								new RedditSubmissionDTO(1611233143L, "https://facebook.com")
						)
				)));
		server.expect(ExpectedCount.manyTimes(),requestTo("https://api.pushshift.io/reddit/search/submission/?size=100&q=testquery"))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));

		List<UrlResponseDTO> testResponse = pushshiftService.findByQuery("testquery", 100);
		server.verify();

		Assert.assertEquals(testResponse.get(0).getUrl(), "https://google.com");
		Assert.assertEquals(testResponse.get(1).getUrl(), "https://facebook.com");
	}
}