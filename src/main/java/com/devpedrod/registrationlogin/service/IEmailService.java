package com.devpedrod.registrationlogin.service;

import com.devpedrod.registrationlogin.model.User;

public interface IEmailService{
    void sendAccountConfirmation(User user);
}
