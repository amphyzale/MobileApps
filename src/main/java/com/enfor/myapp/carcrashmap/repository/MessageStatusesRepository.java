package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.MessageStatus;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface MessageStatusesRepository extends Repository<MessageStatus, Long> {
    Set<MessageStatus> findAll();

    MessageStatus findById(Long id);
}
