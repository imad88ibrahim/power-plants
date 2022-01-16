package com.test.powerplants.service;

import com.test.powerplants.converter.PowerPlantsConverter;
import com.test.powerplants.dto.PlantsGenerationDto;
import com.test.powerplants.dto.PowerPlantsDto;
import com.test.powerplants.entity.PowerPlantsEnt;
import com.test.powerplants.repo.PowerPlantsRepo;
import com.test.powerplants.service.impl.PowerPlantsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PowerPlantsServiceTest {

    @InjectMocks
    private PowerPlantsServiceImpl powerPlantsService;
    @Mock
    private PowerPlantsRepo powerPlantsRepo;
    @Mock
    private PowerPlantsConverter powerPlantsConverter;

    @Test
    void displayTopNOrderByTotalGenTest() {
        List<PowerPlantsEnt> samplePowerPlantsEntList = getSamplePowerPlantsEntList();
        Page<PowerPlantsEnt> result = new PageImpl<>(samplePowerPlantsEntList);
        Mockito.when(powerPlantsRepo.findAll(Mockito.any(Pageable.class))).thenReturn(result);
        Mockito.when(powerPlantsConverter.mapToPowerPlantsDto(Mockito.any(PowerPlantsEnt.class)))
                .thenReturn(getSamplePowerPlantsDto());
        List<PowerPlantsDto> powerPlantsDtoList = powerPlantsService.displayTopNOrderByTotalGen(1);
        Assertions.assertNotNull(powerPlantsDtoList);
        Assertions.assertEquals(samplePowerPlantsEntList.get(0).getSeqGen(), powerPlantsDtoList.get(0).getSeqGen());
    }

    @Test
    void displayBottomNOrderByTotalGenTest() {
        List<PowerPlantsEnt> samplePowerPlantsEntList = getSamplePowerPlantsEntList();
        Page<PowerPlantsEnt> result = new PageImpl<>(samplePowerPlantsEntList);
        Mockito.when(powerPlantsRepo.findAll(Mockito.any(Pageable.class))).thenReturn(result);
        Mockito.when(powerPlantsConverter.mapToPowerPlantsDto(Mockito.any(PowerPlantsEnt.class)))
                .thenReturn(getSamplePowerPlantsDto());
        List<PowerPlantsDto> powerPlantsDtoList = powerPlantsService.displayBottomNOrderByTotalGen(1);
        Assertions.assertNotNull(powerPlantsDtoList);
        Assertions.assertEquals(samplePowerPlantsEntList.get(0).getSeqGen(), powerPlantsDtoList.get(0).getSeqGen());
    }

    @Test
    void filterByLocationTest() {
        Mockito.when(powerPlantsRepo.findByLocation("location", PageRequest.of(0, 1)))
                .thenReturn(getSamplePowerPlantsEntList());
        Mockito.when(powerPlantsConverter.mapToPowerPlantsDto(Mockito.any(PowerPlantsEnt.class)))
                .thenReturn(getSamplePowerPlantsDto());
        List<PowerPlantsDto> byLocation = powerPlantsService.filterByLocation("location", 0, 1);
        Assertions.assertNotNull(byLocation);
        Assertions.assertNotNull(byLocation.get(0).getLocation());
    }

    @Test
    void getPlantsDetailsTest() {
        Mockito.when(powerPlantsRepo.findById(1l)).thenReturn(Optional.of(getSamplePowerPlantsEnt()));
        Mockito.when(powerPlantsConverter.mapToPowerPlantsDto(Mockito.any(PowerPlantsEnt.class)))
                .thenReturn(getSamplePowerPlantsDto());
        PowerPlantsDto plantsDetails = powerPlantsService.getPlantsDetails(1l);
        Assertions.assertNotNull(plantsDetails);
        Assertions.assertNotNull(plantsDetails.getSeqGen());
    }

    @Test
    void getBothActualAndPercentageByLocationTest() {
        Mockito.when(powerPlantsRepo.findByLocation("location", PageRequest.of(0, 1)))
                .thenReturn(getSamplePowerPlantsEntList());
        Mockito.when(powerPlantsConverter.mapToPlantsGenerationDto(Mockito.any(PowerPlantsEnt.class)))
                .thenReturn(getSamplePlantsGenerationDto());
        List<PlantsGenerationDto> listByLocation = powerPlantsService.getBothActualAndPercentageByLocation("location", 0, 1);
        Assertions.assertNotNull(listByLocation);
        Assertions.assertNotNull(listByLocation.get(0).getGenerationValue());
        Assertions.assertNotNull(listByLocation.get(0).getGenerationPercentage());
    }

    private List<PowerPlantsEnt> getSamplePowerPlantsEntList() {
        PowerPlantsEnt samplePowerPlantsEnt1 = getSamplePowerPlantsEnt();
        PowerPlantsEnt samplePowerPlantsEnt2 = getSamplePowerPlantsEnt();
        samplePowerPlantsEnt1.setNetGeneration(2.0);
        samplePowerPlantsEnt2.setNetGeneration(3.0);
        return Arrays.asList(samplePowerPlantsEnt1, samplePowerPlantsEnt2);
    }

    private PowerPlantsEnt getSamplePowerPlantsEnt() {
        PowerPlantsEnt powerPlantsEnt = new PowerPlantsEnt();
        powerPlantsEnt.setSeqGen(1l);
        powerPlantsEnt.setName("name");
        powerPlantsEnt.setLocation("location");
        powerPlantsEnt.setDateYear(1999l);
        powerPlantsEnt.setGeneratorId("getId");
        powerPlantsEnt.setGeneratorStatus("status");
        return powerPlantsEnt;
    }

    private PowerPlantsDto getSamplePowerPlantsDto() {
        PowerPlantsDto powerPlantsDto = new PowerPlantsDto();
        powerPlantsDto.setSeqGen(1l);
        powerPlantsDto.setName("name");
        powerPlantsDto.setLocation("location");
        powerPlantsDto.setDateYear(1999l);
        powerPlantsDto.setGeneratorId("getId");
        powerPlantsDto.setGeneratorStatus("status");
        return powerPlantsDto;
    }

    private PlantsGenerationDto getSamplePlantsGenerationDto() {
        PlantsGenerationDto plantsGenerationDto = new PlantsGenerationDto();
        plantsGenerationDto.setGenerationValue(1.0);
        plantsGenerationDto.setGenerationPercentage("1 %");
        return plantsGenerationDto;
    }

}
