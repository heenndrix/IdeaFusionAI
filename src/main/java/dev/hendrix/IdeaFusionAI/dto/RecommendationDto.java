package dev.hendrix.IdeaFusionAI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendationDto {

    private Long id;
    private List<String> topics;
    private LocalDate createdAt;

}
