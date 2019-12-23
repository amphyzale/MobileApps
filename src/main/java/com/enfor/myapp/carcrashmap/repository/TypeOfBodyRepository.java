package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.TypeOfBody;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface TypeOfBodyRepository extends Repository<TypeOfBody, Long> {
    Set<TypeOfBody> findAll();

    TypeOfBody findById(Long id);

    Long findIdByName(String name);

    TypeOfBody findByName(String name);
}
