package dev.hendrix.IdeaFusionAI.mapper;

import dev.hendrix.IdeaFusionAI.dto.RecommendationDto;
import dev.hendrix.IdeaFusionAI.entity.Recommendation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {

    RecommendationDto toDto(Recommendation recommendation);

    Recommendation toEntity( RecommendationDto recommendationDto);

    List<RecommendationDto> toDtoList(List<Recommendation> recommendations);



}
