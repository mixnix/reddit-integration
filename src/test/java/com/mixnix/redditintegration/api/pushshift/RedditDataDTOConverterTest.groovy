package com.mixnix.redditintegration.api.pushshift

import spock.lang.Specification

class RedditDataDTOConverterTest extends Specification {

    def converterService = new RedditDataDTOConverter()

    def "ConvertToRedditResponseDTO method should convert RedditDataDTO to RedditResponseDTO"() {
        given:
        def redditData = createRedditDataDTO()
        when:
        def redditResponse = converterService.convertToRedditResponseDTO(redditData)
        then:
        redditResponse.getUrls().size() == 2
        redditResponse.getUrls().get(0).equals("google.com")
        redditResponse.getUrls().get(1).equals("reddit.com")
    }

    RedditDataDTO createRedditDataDTO() {
        def submissions = Arrays.asList(
                new RedditSubmissionDTO(1111l, "google.com"),
                new RedditSubmissionDTO(1112l, "reddit.com")
        )
        return new RedditDataDTO(submissions)
    }
}
