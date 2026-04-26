package org.example.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {


    @GetMapping("/{id}")
    public Map<String, Object> getProduct(@PathVariable String id) {

        return Map.of(
                "id", id,
                "name", "MacBook Pro",
                "description", "16GB RAM, M3 chip",
                "category", "Laptops"
        );
    }
}
