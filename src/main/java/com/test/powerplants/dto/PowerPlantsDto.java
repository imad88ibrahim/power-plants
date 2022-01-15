package com.test.powerplants.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PowerPlantsDto {

    private Long seqGen;
    private Long dateYear;
    private String location;
    private String name;
    private String generatorId;
    private String generatorStatus;
    private Double netGeneration;
}
