package com.devpedrod.registrationlogin.service.impl;

import com.devpedrod.registrationlogin.model.ConfirmationToken;
import com.devpedrod.registrationlogin.model.User;
import com.devpedrod.registrationlogin.service.IConfirmationTokenService;
import com.devpedrod.registrationlogin.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private IConfirmationTokenService tokenService;


    @Override
    @Async
    public void sendAccountConfirmation(User user) {
        String token = UUID.randomUUID().toString();
        tokenService.create(new ConfirmationToken(token, now().plusMinutes(20), user));
        String EMAIL_HTML = "Link expire in 20 minutes <br>"
                +"<a href="+"http://localhost:8080/api/v1/users/confirm-account?token="+token
                +"><button"+">Confirm Now</button></a>";

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(EMAIL_HTML, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Confirm your email");
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }
}
