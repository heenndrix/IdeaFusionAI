package dev.hendrix.IdeaFusionAI.controller;

import dev.hendrix.IdeaFusionAI.dto.RecommendationDto;
import dev.hendrix.IdeaFusionAI.entity.Recommendation;
import dev.hendrix.IdeaFusionAI.service.IdeaService;
import dev.hendrix.IdeaFusionAI.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/ideas")
public class IdeaController {

    private final IdeaService ideaService;
    private final RecommendationService recommendationService;

    public IdeaController(IdeaService ideaService, RecommendationService recommendationService) {
        this.ideaService = ideaService;
        this.recommendationService = recommendationService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateIdea() {
        List<RecommendationDto> recommendation = recommendationService.getAll();
        return ideaService.generateIdea(recommendation)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
