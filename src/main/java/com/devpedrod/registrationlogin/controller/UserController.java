package com.devpedrod.registrationlogin.controller;

import com.devpedrod.registrationlogin.controller.exceptions.Response;
import com.devpedrod.registrationlogin.dto.UserCreateDto;
import com.devpedrod.registrationlogin.model.User;
import com.devpedrod.registrationlogin.service.IConfirmationTokenService;
import com.devpedrod.registrationlogin.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IConfirmationTokenService tokenService;

    @PostMapping("")
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserCreateDto object) {
        User user = new User();
        BeanUtils.copyProperties(object, user);
        userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(CREATED)
                .statusCode(CREATED.value())
                .message("Object created successfully! ID: " + user.getId())
                .build());
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<String> confirmAccount(@RequestParam("token") String token) {
        tokenService.validateAndConfirmAccount(token);
        return ResponseEntity.ok("Confirmed!");
    }
}
