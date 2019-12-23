package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.BrandOfCar;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface BrandOfCarRepository extends Repository<BrandOfCar, Long> {
    Set<BrandOfCar> findAll();

    BrandOfCar findById(Long id);
    BrandOfCar findByName(String name);
}
