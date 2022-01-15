package com.test.powerplants.service.impl;

import com.test.powerplants.converter.PowerPlantsConverter;
import com.test.powerplants.dto.PlantsGenerationDto;
import com.test.powerplants.dto.PowerPlantsDto;
import com.test.powerplants.entity.PowerPlantsEnt;
import com.test.powerplants.repo.PowerPlantsRepo;
import com.test.powerplants.service.PowerPlantsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PowerPlantsServiceImpl implements PowerPlantsService {

    private PowerPlantsRepo powerPlantsRepo;
    private PowerPlantsConverter powerPlantsConverter;

    @Override
    public List<PowerPlantsDto> displayTopNOrderByTotalGen(int count) {
        return displayOrderByTotalGen(count, Sort.Direction.ASC.name());
    }

    @Override
    public List<PowerPlantsDto> displayBottomNOrderByTotalGen(int count) {
        return displayOrderByTotalGen(count, Sort.Direction.DESC.name());
    }

    @Override
    public List<PowerPlantsDto> filterByLocation(String location, int pageNumber, int pageSize) {
        return powerPlantsRepo.findByLocation(location, PageRequest.of(pageNumber, pageSize)).stream()
                .map(powerPlantsConverter::mapToPowerPlantsDto).collect(Collectors.toList());
    }

    @Override
    public PowerPlantsDto getPlantsDetails(long seqGen) {
        PowerPlantsEnt powerPlantsEnt = powerPlantsRepo.findById(seqGen).orElseGet(PowerPlantsEnt::new);
        return powerPlantsConverter.mapToPowerPlantsDto(powerPlantsEnt);
    }

    @Override
    public List<PlantsGenerationDto> getBothActualAndPercentageByLocation(String location, int pageNumber, int pageSize) {
        return powerPlantsRepo.findByLocation(location, PageRequest.of(pageNumber, pageSize)).stream()
                .map(powerPlantsConverter::mapToPlantsGenerationDto).collect(Collectors.toList());
    }

    private List<PowerPlantsDto> displayOrderByTotalGen(int count, String direction) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), "netGeneration");
        Pageable pageable = PageRequest.of(0, count, sort);
        return powerPlantsRepo.findAll(pageable).stream()
                .map(powerPlantsConverter::mapToPowerPlantsDto).collect(Collectors.toList());
    }
}
