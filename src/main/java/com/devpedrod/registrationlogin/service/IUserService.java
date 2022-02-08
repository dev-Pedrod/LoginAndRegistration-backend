package com.devpedrod.registrationlogin.service;

import com.devpedrod.registrationlogin.model.User;

public interface IUserService {
    void create(User object);
    void enableUser(String email);
}
