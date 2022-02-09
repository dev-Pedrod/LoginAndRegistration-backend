package com.devpedrod.registrationlogin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ConfirmationToken{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    private String token;
    @NotNull
    private LocalDateTime expiresAt;
    @NotNull
    private LocalDateTime createdAt;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(name = "_user_id")
    private User user;

    public ConfirmationToken(String token, LocalDateTime expiresAt, User user) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
