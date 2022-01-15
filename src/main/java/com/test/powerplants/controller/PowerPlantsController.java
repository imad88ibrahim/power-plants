package com.test.powerplants.controller;

import com.test.powerplants.dto.PlantsGenerationDto;
import com.test.powerplants.dto.PowerPlantsDto;
import com.test.powerplants.service.PowerPlantsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/power-plants")
public class PowerPlantsController {

    private PowerPlantsService powerPlantsService;

    @GetMapping("/{seqGen}")
    public PowerPlantsDto displayBottomNOrderByTotalGen(@PathVariable("seqGen") long seqGen) {
        return powerPlantsService.getPlantsDetails(seqGen);
    }


    @GetMapping("/display-top/{count}")
    public List<PowerPlantsDto> displayTopNOrderByTotalGen(@PathVariable("count") int count) {
        return powerPlantsService.displayTopNOrderByTotalGen(count);
    }

    @GetMapping("/display-bottom/{count}")
    public List<PowerPlantsDto> displayBottomNOrderByTotalGen(@PathVariable("count") int count) {
        return powerPlantsService.displayBottomNOrderByTotalGen(count);
    }

    @GetMapping("/by-location/{location}")
    public List<PowerPlantsDto> filterByLocation(@PathVariable("location") String location,
                                                 @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return powerPlantsService.filterByLocation(location, pageNumber, pageSize);
    }

    @GetMapping("/by-location/{location}/display-actual-percentage")
    public List<PlantsGenerationDto> getBothActualAndPercentageByLocation(@PathVariable("location") String location,
                                                                          @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return powerPlantsService.getBothActualAndPercentageByLocation(location, pageNumber, pageSize);
    }

}
