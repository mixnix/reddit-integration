package com.mixnix.redditintegration.controller;

import com.mixnix.redditintegration.api.pushshift.PushshiftService;
import com.mixnix.redditintegration.api.pushshift.RedditResponseDTO;
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
    public ResponseEntity<RedditResponseDTO> getMemes(
            @RequestParam @NotEmpty(message="Query string shouldn't be empty") String searchQuery,
            @RequestParam(defaultValue = "100")
                @Min(value=1, message="Minimum page size is 1")
                @Max(value=100, message="Maximum page siez is 100") int pageSize
    ){
        log.info("GET /api/memes/?searchQuery={}", searchQuery);

        return ResponseEntity.ok().body(pushshiftService.findByQuery(searchQuery, pageSize));
    }
}
