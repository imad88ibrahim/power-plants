package com.test.powerplants.service;

import com.test.powerplants.dto.PlantsGenerationDto;
import com.test.powerplants.dto.PowerPlantsDto;

import java.util.List;

public interface PowerPlantsService {

    /**
     * 1. Ability to display top N and bottom N plants in either descending or ascending order by
     * total generation output
     *
     * @param count
     * @return
     */
    List<PowerPlantsDto> displayTopNOrderByTotalGen(int count);

    /**
     * 1. Ability to display top N and bottom N plants in either descending or ascending order by
     * total generation output
     *
     * @param count
     * @return
     */
    List<PowerPlantsDto> displayBottomNOrderByTotalGen(int count);

    /**
     * 2. Ability to filter power plants by location
     * 3. Ability to paginate filtered power plants by location
     *
     * @param location
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<PowerPlantsDto> filterByLocation(String location, int pageNumber, int pageSize);

    /**
     * 4. Ability to see details of a single power plant
     *
     * @param seqGen
     * @return
     */
    PowerPlantsDto getPlantsDetails(long seqGen);

    /**
     * 5. Show both actual and percentage values of the plants' generation output by location
     *
     * @param location
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<PlantsGenerationDto> getBothActualAndPercentageByLocation(String location, int pageNumber, int pageSize);

}
