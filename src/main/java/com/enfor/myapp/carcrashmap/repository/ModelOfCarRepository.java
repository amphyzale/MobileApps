package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.ModelOfCar;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface ModelOfCarRepository extends Repository<ModelOfCar, Long> {
    Set<ModelOfCar> findAll();

    Long findIdByName(String name);

    ModelOfCar findById(Long id);

}
