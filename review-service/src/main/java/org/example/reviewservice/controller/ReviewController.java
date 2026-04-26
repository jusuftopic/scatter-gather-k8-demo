package org.example.reviewservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @GetMapping("/{id}")
    public Map<String, Object> getReviews(@PathVariable String id) {
        return Map.of(
                "id", id,
                "rating", 4.6,
                "count", 128
        );
    }
}
