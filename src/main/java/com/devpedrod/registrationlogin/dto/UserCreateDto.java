package com.devpedrod.registrationlogin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateDto {

    @NotNull(message = "The username cannot be empty.")
    @NotBlank(message = "The username cannot be blank.")
    @Length(min = 2, max = 16, message = "the length must be between 2 and 16.")
    private String username;

    @NotNull(message = "The password cannot be empty.")
    @NotBlank(message = "The password cannot be blank.")
    @Length(min = 8, max = 32, message = "the length must be between 8 and 32.")
    private String password;

    @Email(message = "must be a well formed email address.")
    private String email;
}
