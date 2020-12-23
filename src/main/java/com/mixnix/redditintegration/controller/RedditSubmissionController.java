package com.mixnix.redditintegration.controller;

import com.mixnix.redditintegration.api.pushshift.PushshiftService;
import com.mixnix.redditintegration.api.pushshift.RedditResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
@Slf4j
public class RedditSubmissionController {

    private final PushshiftService pushshiftService;

    @GetMapping
    public ResponseEntity<RedditResponseDTO> getMemes(@RequestParam String searchQuery){
        log.info("GET /api/memes/?subredditName={}", searchQuery);

        return ResponseEntity.ok().body(pushshiftService.findByQuery(searchQuery));
    }
}
