package dev.hendrix.IdeaFusionAI.controller;

import dev.hendrix.IdeaFusionAI.dto.RecommendationDto;
import dev.hendrix.IdeaFusionAI.mapper.RecommendationMapper;
import dev.hendrix.IdeaFusionAI.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService, RecommendationMapper recommendationMapper) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/add")
    public ResponseEntity<RecommendationDto> create(@RequestBody RecommendationDto recommendation) {
        RecommendationDto created = recommendationService.create(recommendation);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecommendationDto>> getAll() {
        List<RecommendationDto> recommendations = recommendationService.getAll();
        return ResponseEntity.ok(recommendations);
    }


}
