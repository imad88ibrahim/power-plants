package com.test.powerplants.converter;

import com.test.powerplants.dto.PlantsGenerationDto;
import com.test.powerplants.dto.PowerPlantsDto;
import com.test.powerplants.entity.PowerPlantsEnt;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class PowerPlantsConverter {

    public PowerPlantsDto mapToPowerPlantsDto(PowerPlantsEnt ent) {
        PowerPlantsDto dto = new PowerPlantsDto();
        BeanUtils.copyProperties(ent, dto);
        return dto;
    }

    public PlantsGenerationDto mapToPlantsGenerationDto(PowerPlantsEnt ent) {
        PlantsGenerationDto plantsGenerationDto = new PlantsGenerationDto();
        plantsGenerationDto.setGenerationValue(ent.getNetGeneration());
        plantsGenerationDto.setGenerationPercentage(getPlantsPercentageValue(ent.getNetGeneration()));
        return plantsGenerationDto;
    }

    private String getPlantsPercentageValue(double netGeneration) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(Double.valueOf((netGeneration / 10000000) * 100)) + " %";
    }

}
