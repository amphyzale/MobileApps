package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.TypeOfRoadObj;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RoadObjectsRepository extends CrudRepository<TypeOfRoadObj, Long> {
    Set<TypeOfRoadObj> findAll();
}
