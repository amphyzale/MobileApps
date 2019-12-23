package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.City;
import com.enfor.myapp.carcrashmap.domain.Street;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.Set;
import java.util.concurrent.Future;

public interface StreetsRepository extends CrudRepository<Street, Long> {
    @Async
    Future<Set<Street>> findAllByCity(City city);

    Set<Street> findAll();

    Street findByName(String name);
}
