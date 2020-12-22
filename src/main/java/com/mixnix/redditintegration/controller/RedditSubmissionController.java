package com.mixnix.redditintegration.controller;

import com.mixnix.redditintegration.api.pushshift.PushshiftServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
@Slf4j
public class RedditSubmissionController {

    private final PushshiftServiceImpl pushshiftService;

    @GetMapping
    public ResponseEntity getMemes(@RequestParam String searchQuery){
        log.info("GET /api/memes/?subredditName={}", searchQuery);

        return ResponseEntity.ok().body(pushshiftService.findByQuery(searchQuery));
    }
}
