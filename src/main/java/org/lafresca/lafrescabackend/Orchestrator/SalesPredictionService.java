package org.lafresca.lafrescabackend.Orchestrator;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class SalesPredictionService {
    private final WebClient webClient;
    private final String url = "http://external-api-url.com"; // need to change

    public SalesPredictionService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(url).build(); // Set the base URL
    }

    public Map<String, Map<String, Integer>> getSalesPredictionData() {
        return webClient.get()
                .uri("/endpoint") // need to change
                .retrieve()
                .bodyToMono(Map.class) // Specify the type as Map.class
                .block(); // Block to get the result directly
    }

    public Map<String, Integer> getSalesPredictionDataByCafe(String cafeId) {
        return webClient.get()
                .uri("/endpoint/"+cafeId) // need to change
                .retrieve()
                .bodyToMono(Map.class) // Specify the type as Map.class
                .block(); // Block to get the result directly
    }
}