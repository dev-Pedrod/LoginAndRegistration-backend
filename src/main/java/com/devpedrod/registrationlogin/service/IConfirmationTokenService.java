package com.devpedrod.registrationlogin.service;

import com.devpedrod.registrationlogin.model.ConfirmationToken;

public interface IConfirmationTokenService {
    void create(ConfirmationToken token);
    ConfirmationToken findByToken(String token);
    void validateAndConfirmAccount(String token);
}
