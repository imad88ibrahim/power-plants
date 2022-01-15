package com.test.powerplants.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PlantsGenerationDto {

    private Double generationValue;
    private String generationPercentage;

}
