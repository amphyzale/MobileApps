package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
