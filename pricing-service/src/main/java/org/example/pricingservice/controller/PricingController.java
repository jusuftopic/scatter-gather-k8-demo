package org.example.pricingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pricing")
public class PricingController {

    @GetMapping("/{id}")
    public Map<String, Object> getPrice(@PathVariable String id) {
        return Map.of(
                "id", id,
                "price", 1999,
                "currency", "EUR"
        );
    }

}
