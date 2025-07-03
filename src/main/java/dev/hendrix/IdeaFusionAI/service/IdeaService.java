package dev.hendrix.IdeaFusionAI.service;

import dev.hendrix.IdeaFusionAI.dto.RecommendationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;


import java.util.List;

@Service
public class IdeaService {

    private final WebClient webClient;

    @Value("${API_KEY}")
    private String apiKey;

    public IdeaService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<String> generateIdea(List<RecommendationDto> recommendations) {

        String topicsSummary = recommendations.stream()
                .map(topic -> String.format("%s (ID: %d, Criado em: %s)",
                        topic.getTopics(),
                        topic.getId(),
                        topic.getCreatedAt()))
                .collect(Collectors.joining(", "));

        String prompt = "Com base nos seguintes tópicos: " + topicsSummary +
                ", sugira uma forma criativa de unir esses interesses em uma única ideia.";

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4.1",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é uma IA assitente criativa. Me sugira uma idéia baseada nos tópicos seguintes: " + topicsSummary),
                        Map.of("role", "user", "content", prompt)
                )
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if(choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return (String) message.get("content").toString();
                    }
                    return "Nenhuma receita encontrada.";
                });
    }
}
