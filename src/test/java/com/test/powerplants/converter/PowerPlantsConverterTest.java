package com.test.powerplants.converter;

import com.test.powerplants.dto.PlantsGenerationDto;
import com.test.powerplants.dto.PowerPlantsDto;
import com.test.powerplants.entity.PowerPlantsEnt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PowerPlantsConverterTest {

    @InjectMocks
    private PowerPlantsConverter powerPlantsConverter;

    @Test
    void mapToPowerPlantsDtoTest() {
        PowerPlantsEnt samplePowerPlantsEnt = getSamplePowerPlantsEnt();
        PowerPlantsDto samplePowerPlantsDto = getSamplePowerPlantsDto();
        PowerPlantsDto resultDto = powerPlantsConverter.mapToPowerPlantsDto(samplePowerPlantsEnt);
        Assertions.assertNotNull(resultDto);
        Assertions.assertEquals(resultDto.getSeqGen(), samplePowerPlantsDto.getSeqGen());
        Assertions.assertEquals(resultDto.getLocation(), samplePowerPlantsDto.getLocation());
        Assertions.assertEquals(resultDto.getNetGeneration(), samplePowerPlantsDto.getNetGeneration());
    }

    @Test
    void mapToPlantsGenerationDtoTest() {
        PlantsGenerationDto plantsGenerationDto = powerPlantsConverter
                .mapToPlantsGenerationDto(getSamplePowerPlantsEnt());
        Assertions.assertNotNull(plantsGenerationDto);
        Assertions.assertEquals(plantsGenerationDto.getGenerationValue(), 1000.0);
        Assertions.assertEquals(plantsGenerationDto.getGenerationPercentage(), "0.01 %");
    }

    private PowerPlantsEnt getSamplePowerPlantsEnt() {
        PowerPlantsEnt powerPlantsEnt = new PowerPlantsEnt();
        powerPlantsEnt.setSeqGen(1l);
        powerPlantsEnt.setLocation("location");
        powerPlantsEnt.setNetGeneration(1000.0);
        return powerPlantsEnt;
    }

    private PowerPlantsDto getSamplePowerPlantsDto() {
        PowerPlantsDto powerPlantsDto = new PowerPlantsDto();
        powerPlantsDto.setSeqGen(1l);
        powerPlantsDto.setLocation("location");
        powerPlantsDto.setNetGeneration(1000.0);
        return powerPlantsDto;
    }
}
