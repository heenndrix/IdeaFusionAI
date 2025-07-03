package dev.hendrix.IdeaFusionAI.service;

import dev.hendrix.IdeaFusionAI.dto.RecommendationDto;
import dev.hendrix.IdeaFusionAI.entity.Recommendation;
import dev.hendrix.IdeaFusionAI.mapper.RecommendationMapper;
import dev.hendrix.IdeaFusionAI.repository.RecommendationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final RecommendationMapper recommendationMapper;

    public RecommendationService(RecommendationRepository recommendationRepository, RecommendationMapper recommendationMapper) {
        this.recommendationRepository = recommendationRepository;
        this.recommendationMapper = recommendationMapper;
    }

    //post
    public RecommendationDto create(RecommendationDto recommendationDto) {
        Recommendation recommendation = recommendationMapper.toEntity(recommendationDto);
        Recommendation saved = recommendationRepository.save(recommendation);
        return recommendationMapper.toDto(saved);
    }

    //getAll
    public List<RecommendationDto> getAll() {
        List<Recommendation> recommendations = recommendationRepository.findAll();
        return recommendationMapper.toDtoList(recommendations);
    }






}
