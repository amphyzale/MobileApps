package com.enfor.myapp.carcrashmap.controller;

import com.enfor.myapp.carcrashmap.domain.User;
import com.enfor.myapp.carcrashmap.dto.PasswordChangeDto;
import com.enfor.myapp.carcrashmap.dto.UserDto;
import com.enfor.myapp.carcrashmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserRestController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "getUser/byId/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") String id){
        User user = userService.findById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "getUser/byUsername/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable(name = "name") String name){
        User user = userService.findByName(name);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "profile")
    public ResponseEntity<UserDto> getProfile(@AuthenticationPrincipal Principal principal) {
        String name = principal.getName();
        UserDto userDto = UserDto.fromUser(userService.findByName(name));
        if (userDto.getName() != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "profile/change")
    public ResponseEntity<UserDto> changeProfile(@AuthenticationPrincipal Principal principal,
                                                 @RequestBody UserDto userDto) {
        if (principal.getName() != null && principal.getName().equals(userDto.getName())) {
            User result = userService.findByName(userDto.getName());
            result.setUpdated(LocalDateTime.now());
            result.setLastVisit(LocalDateTime.now());
            result.setFirstName(userDto.getFirstName());
            result.setLastName(userDto.getLastName());
            result.setGender(userDto.getGender());
            result.setLocale(userDto.getLocale());
            result.setUserPic(userDto.getUserPic());
            userService.save(result);
            return new ResponseEntity<>(UserDto.fromUser(result), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "profile/changePassword")
    public ResponseEntity<UserDto> changePassword(@AuthenticationPrincipal Principal principal,
                                                  @RequestBody PasswordChangeDto password) {
        String name = principal.getName();
        UserDto userDto = UserDto.fromUser(userService.findByName(name));
        if (userDto.getName() != null && password.getPassword().equals(password.getConfirmedPassword())) {
            User result = userService.findByName(userDto.getName());
            result.setPassword(passwordEncoder.encode(password.getPassword()));
            userService.save(result);
            return new ResponseEntity<>(UserDto.fromUser(result), HttpStatus.OK);
        }
        throw new BadCredentialsException("Passwords are not equals!");
    }
}