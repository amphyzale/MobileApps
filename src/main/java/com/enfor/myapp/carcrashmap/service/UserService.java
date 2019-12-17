package com.enfor.myapp.carcrashmap.service;

import com.enfor.myapp.carcrashmap.domain.User;
import com.enfor.myapp.carcrashmap.dto.GuestDto;

import java.util.List;

public interface UserService {
    User register(GuestDto guestDto);
    List<User> getAll();
    User findByName(String name);
    User findById(String id);
    void delete(String id);

    boolean save(User user);
}
