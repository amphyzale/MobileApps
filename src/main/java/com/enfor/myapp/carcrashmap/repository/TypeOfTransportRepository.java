package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.TypeOfTransport;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface TypeOfTransportRepository extends Repository<TypeOfTransport, Long> {
    Set<TypeOfTransport> findAll();

    TypeOfTransport findById(Long id);

    Long findIdByName(String name);

    TypeOfTransport findByName(String name);
}
