package com.mixnix.redditintegration.controller;

import com.mixnix.redditintegration.api.pushshift.service.PushshiftService;
import com.mixnix.redditintegration.api.pushshift.domain.UrlsResponseDTO;
import com.mixnix.redditintegration.domain.memes.MemesDownloadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
@Slf4j
@Validated
public class RedditSubmissionController {

    private final PushshiftService pushshiftService;

    private final MemesDownloadService memesDownloadService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach((error) -> {
            String fieldName = error.getPropertyPath().toString();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping
    public ResponseEntity<UrlsResponseDTO> getMemes(
            @RequestParam @NotEmpty(message="Query string shouldn't be empty") String searchQuery,
            @RequestParam(defaultValue = "100")
                @Min(value=1, message="Minimum page size is 1")
                @Max(value=100, message="Maximum page siez is 100") int pageSize
    ){
        log.info("GET /api/memes/?searchQuery={}", searchQuery);

        return ResponseEntity.ok().body(pushshiftService.findByQuery(searchQuery, pageSize));
    }

    @PostMapping
    public ResponseEntity<UrlsResponseDTO> saveMemestoDatabase(
            @RequestParam @NotEmpty(message="Memes subreddit shouldn't be empty") String subreddit,
            @RequestParam(defaultValue = "100")
            @Min(value=1, message="Minimum page size is 1")
            @Max(value=100, message="Maximum page siez is 100") int pageSize
    ){
        log.info("POST /api/memes/?subredditName={}", subreddit);

        return ResponseEntity.ok().body(memesDownloadService.saveMemesToDatabase(subreddit, pageSize));
    }
}
