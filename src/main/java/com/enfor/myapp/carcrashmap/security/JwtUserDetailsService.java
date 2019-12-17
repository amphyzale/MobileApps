package com.enfor.myapp.carcrashmap.security;

import com.enfor.myapp.carcrashmap.domain.User;
import com.enfor.myapp.carcrashmap.security.jwt.JwtUserFactory;
import com.enfor.myapp.carcrashmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + name + " not found!");
        }

        return JwtUserFactory.create(user);
    }
}
