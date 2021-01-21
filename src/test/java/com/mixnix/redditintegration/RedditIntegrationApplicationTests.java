package com.mixnix.redditintegration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mixnix.redditintegration.api.pushshift.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
//@SpringBootTest
@RestClientTest({PushshiftService.class, RedditIntegrationApplication.class})
class RedditIntegrationApplicationTests {

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private PushshiftServiceImpl pushshiftService;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private MockRestServiceServer server;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
//		server = MockRestServiceServer.createServer(restTemplate);

		String detailsString =
				objectMapper.writeValueAsString(new RedditDataDTO(new ArrayList<RedditSubmissionDTO>(
						Arrays.asList(
								new RedditSubmissionDTO(1611234261L, "https://google.com"),
								new RedditSubmissionDTO(1611233143L, "https://facebook.com")
								)
				)));

		this.server.expect(requestTo("https://api.pushshift.io/reddit/search/submission/?size=100" +
				"&q=testquery"))
				.andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
	}


	@Test
	void contextLoads() {
		RedditResponseDTO testResponse = pushshiftService.findByQuery("testquery", 100);
		System.out.println("done");
		assert 1 == 1;
	}

}