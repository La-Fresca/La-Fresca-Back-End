package org.lafresca.lafrescabackend.Orchestrator;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class StockPredictionService {
    private final WebClient webClient;
    private final String url = "http://external-api-url.com"; // need to change

    public StockPredictionService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(url).build(); // Set the base URL
    }

    public Map<String, Map<String, Float>> getBranchData() {
        return webClient.get()
                .uri("/endpoint") // need to change
                .retrieve()
                .bodyToMono(Map.class) // Specify the type as Map.class
                .block(); // Block to get the result directly
    }

}
