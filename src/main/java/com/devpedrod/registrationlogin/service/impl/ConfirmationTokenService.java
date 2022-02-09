package com.devpedrod.registrationlogin.service.impl;

import com.devpedrod.registrationlogin.model.ConfirmationToken;
import com.devpedrod.registrationlogin.repository.ConfirmationTokenRepository;
import com.devpedrod.registrationlogin.service.IConfirmationTokenService;
import com.devpedrod.registrationlogin.service.IUserService;
import com.devpedrod.registrationlogin.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository tokenRepository;
    @Autowired
    private IUserService userService;

    @Override
    public void create(ConfirmationToken token) {
        token.setCreatedAt(now());
        tokenRepository.save(token);
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new ObjectNotFoundException("Token not found. token: " + token));
    }

    @Override
    public void validateAndConfirmAccount(String token) {
        ConfirmationToken cToken = findByToken(token);
        if(cToken.getConfirmedAt() != null) {
            throw new IllegalArgumentException("Email already confirmed");
        }
        if (cToken.getExpiresAt().isBefore(now())) {
            throw new IllegalArgumentException("Token expired");
        }
        cToken.setConfirmedAt(now());
        tokenRepository.updateConfirmedAt(token, now());
        userService.enableUser(cToken.getUser().getEmail());
    }
}
