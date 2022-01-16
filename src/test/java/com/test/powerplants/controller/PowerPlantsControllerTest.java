package com.test.powerplants.controller;

import com.test.powerplants.dto.PlantsGenerationDto;
import com.test.powerplants.dto.PowerPlantsDto;
import com.test.powerplants.service.impl.PowerPlantsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PowerPlantsControllerTest {

    @InjectMocks
    private PowerPlantsController powerPlantsController;
    @Mock
    private PowerPlantsServiceImpl powerPlantsService;

    @Test
    void getPlantsDetailsTest() {
        PowerPlantsDto samplePowerPlantsDto = getSamplePowerPlantsDto();
        Mockito.when(powerPlantsService.getPlantsDetails(1l)).thenReturn(samplePowerPlantsDto);
        PowerPlantsDto plantsDetails = powerPlantsController.getPlantsDetails(1l);
        Assertions.assertNotNull(plantsDetails);
        Assertions.assertEquals(samplePowerPlantsDto.getSeqGen(), plantsDetails.getSeqGen());
    }

    @Test
    void displayTopNOrderByTotalGenTest() {
        PowerPlantsDto samplePowerPlantsDto = getSamplePowerPlantsDto();
        Mockito.when(powerPlantsService.displayTopNOrderByTotalGen(1)).thenReturn(Arrays.asList(samplePowerPlantsDto));
        List<PowerPlantsDto> powerPlantsDtoList = powerPlantsController.displayTopNOrderByTotalGen(1);
        Assertions.assertNotNull(powerPlantsDtoList);
        Assertions.assertEquals(samplePowerPlantsDto.getSeqGen(), powerPlantsDtoList.get(0).getSeqGen());
    }


    @Test
    void displayBottomNOrderByTotalGenTest() {
        PowerPlantsDto samplePowerPlantsDto = getSamplePowerPlantsDto();
        Mockito.when(powerPlantsService.displayBottomNOrderByTotalGen(1)).thenReturn(Arrays.asList(samplePowerPlantsDto));
        List<PowerPlantsDto> powerPlantsDtoList = powerPlantsController.displayBottomNOrderByTotalGen(1);
        Assertions.assertNotNull(powerPlantsDtoList);
        Assertions.assertEquals(samplePowerPlantsDto.getSeqGen(), powerPlantsDtoList.get(0).getSeqGen());
    }

    @Test
    void filterByLocationTest() {
        PowerPlantsDto samplePowerPlantsDto = getSamplePowerPlantsDto();
        Mockito.when(powerPlantsService.filterByLocation("location", 0, 1))
                .thenReturn(Arrays.asList(samplePowerPlantsDto));
        List<PowerPlantsDto> byLocation = powerPlantsController.filterByLocation("location", 0, 1);
        Assertions.assertNotNull(byLocation);
        Assertions.assertNotNull(byLocation.get(0).getLocation());
    }

    @Test
    void getBothActualAndPercentageByLocationTest() {
        PowerPlantsDto samplePowerPlantsDto = getSamplePowerPlantsDto();
        Mockito.when(powerPlantsService.getBothActualAndPercentageByLocation("location", 0, 1))
                .thenReturn(Arrays.asList(getSamplePlantsGenerationDto()));
        List<PlantsGenerationDto> listByLocation = powerPlantsController.getBothActualAndPercentageByLocation("location", 0, 1);
        Assertions.assertNotNull(listByLocation);
        Assertions.assertNotNull(listByLocation.get(0).getGenerationValue());
        Assertions.assertNotNull(listByLocation.get(0).getGenerationPercentage());
    }

    private PowerPlantsDto getSamplePowerPlantsDto() {
        PowerPlantsDto powerPlantsDto = new PowerPlantsDto();
        powerPlantsDto.setSeqGen(1l);
        powerPlantsDto.setName("name");
        powerPlantsDto.setLocation("location");
        powerPlantsDto.setDateYear(1999l);
        powerPlantsDto.setGeneratorId("getId");
        powerPlantsDto.setGeneratorStatus("status");
        powerPlantsDto.setNetGeneration(1.0);
        return powerPlantsDto;
    }

    private PlantsGenerationDto getSamplePlantsGenerationDto() {
        PlantsGenerationDto plantsGenerationDto = new PlantsGenerationDto();
        plantsGenerationDto.setGenerationValue(1.0);
        plantsGenerationDto.setGenerationPercentage("1 %");
        return plantsGenerationDto;
    }
}
