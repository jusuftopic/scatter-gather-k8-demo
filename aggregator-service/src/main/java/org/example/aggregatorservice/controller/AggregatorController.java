package org.example.aggregatorservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aggregate")
public class AggregatorController {


    private final WebClient webClient;

    public AggregatorController(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Scatter/Gather aggregation pattern:
     *
     * SCATTER PHASE:
     * - Calls multiple microservices in parallel
     * - Each service owns its own domain data
     *
     * GATHER PHASE:
     * - Combines responses into a single product view
     *
     * Kubernetes Context:
     * - Service discovery is handled via Kubernetes DNS
     * - No localhost usage in cluster environment
     * - Services communicate via stable service names
     */
    @GetMapping("/product/{id}")
    public Map<String, Object> getProduct(@PathVariable String id) {
        /* product details */
        Mono<Map> productMono = webClient.get()
                .uri("http://product-service/product/" + id)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofSeconds(2))
                .onErrorResume(ex -> Mono.just(Map.of("error", "service unavailable")));

        /* pricing details */
        Mono<Map> pricingMono = webClient.get()
                .uri("http://pricing-service/pricing/" + id)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofSeconds(2))
                .onErrorResume(ex -> Mono.just(Map.of("error", "service unavailable")));

        /* inventory details */
        Mono<Map> inventoryMono = webClient.get()
                .uri("http://inventory-service/inventory/" + id)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofSeconds(2))
                .onErrorResume(ex -> Mono.just(Map.of("error", "service unavailable")));


        /* review details */
        Mono<Map> reviewMono = webClient.get()
                .uri("http://review-service/reviews/" + id)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofSeconds(2))
                .onErrorResume(ex -> Mono.just(Map.of("error", "service unavailable")));


        return Mono.zip(productMono, pricingMono, inventoryMono, reviewMono)
                .map(tuple -> {
                    Map<String, Object> result = new HashMap<>();

                    result.put("product", tuple.getT1());
                    result.put("pricing", tuple.getT2());
                    result.put("inventory", tuple.getT3());
                    result.put("reviews", tuple.getT4());

                    return result;
                })
                .block();
    }
}
