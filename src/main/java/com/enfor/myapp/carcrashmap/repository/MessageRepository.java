package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    void deleteById(Long id);
}
