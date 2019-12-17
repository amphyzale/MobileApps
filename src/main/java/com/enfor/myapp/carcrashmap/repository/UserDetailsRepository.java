package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
    User findByName(String name);
}

