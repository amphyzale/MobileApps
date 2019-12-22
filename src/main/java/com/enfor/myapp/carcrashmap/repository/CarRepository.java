package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CarRepository extends CrudRepository<Car, Long> {
    Set<Car> findAll();

}
