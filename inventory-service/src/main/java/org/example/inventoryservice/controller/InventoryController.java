package org.example.inventoryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @GetMapping("/{id}")
    public Map<String, Object> getStock(@PathVariable String id) {
        return Map.of(
                "id", id,
                "inStock", true,
                "quantity", 42
        );
    }
}
