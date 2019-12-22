package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.City;
import com.enfor.myapp.carcrashmap.domain.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CitiesRepository extends CrudRepository<City, Long> {

    Set<City> findAllByRegion(Region region);

    Set<City> findCitiesByRegionId(Long id);

    Set<City> findAll();
}
