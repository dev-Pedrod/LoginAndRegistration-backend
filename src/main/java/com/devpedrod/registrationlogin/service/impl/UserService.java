package com.devpedrod.registrationlogin.service.impl;

import com.devpedrod.registrationlogin.enums.UserRole;
import com.devpedrod.registrationlogin.model.User;
import com.devpedrod.registrationlogin.repository.UserRepository;
import com.devpedrod.registrationlogin.service.IEmailService;
import com.devpedrod.registrationlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IEmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->  new UsernameNotFoundException(String.format("user with email %s not found", email)));
    }

    @Override
    @Transactional
    public void create(User object) {
        if (userRepository.findByEmail(object.getEmail()).isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        object.setPassword(bCryptPasswordEncoder.encode(object.getPassword()));
        userRepository.saveAndFlush(object);
        emailService.sendAccountConfirmation(object);
    }

    @Override
    @Transactional
    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}
