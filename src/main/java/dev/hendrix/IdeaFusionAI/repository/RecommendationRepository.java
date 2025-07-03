package dev.hendrix.IdeaFusionAI.repository;

import dev.hendrix.IdeaFusionAI.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long>{

}
