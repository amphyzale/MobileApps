package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RegionsRepository extends CrudRepository<Region, Long> {
    Set<Region> findAll();
    Region findByName(String name);
}
