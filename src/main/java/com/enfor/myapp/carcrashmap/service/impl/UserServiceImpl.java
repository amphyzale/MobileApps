package com.enfor.myapp.carcrashmap.service.impl;

import com.enfor.myapp.carcrashmap.domain.Role;
import com.enfor.myapp.carcrashmap.domain.Status;
import com.enfor.myapp.carcrashmap.domain.User;
import com.enfor.myapp.carcrashmap.dto.GuestDto;
import com.enfor.myapp.carcrashmap.exceptions.UserNotFoundException;
import com.enfor.myapp.carcrashmap.repository.RoleRepository;
import com.enfor.myapp.carcrashmap.repository.UserDetailsRepository;
import com.enfor.myapp.carcrashmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsRepository userDetailsRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDetailsRepository userDetailsRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(GuestDto guestDto) {
        User user = guestDto.toUser();
        Role role = roleRepository.findByName("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        user.setCreated(LocalDateTime.now());
        user.setLastVisit(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        return userDetailsRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userDetailsRepository.findAll();
    }

    @Override
    public User findByName(String name) {
        return userDetailsRepository.findByName(name);
    }

    @Override
    public User findById(String id) {
        return userDetailsRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void delete(String id) {
        userDetailsRepository.deleteById(id);
    }

    @Override
    public boolean save(User user) {
        userDetailsRepository.save(user);
        return true;
    }
}
