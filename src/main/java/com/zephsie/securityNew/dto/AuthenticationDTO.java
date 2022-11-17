package com.zephsie.securityNew.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationDTO {
    @NotEmpty(message = "Username is required")
    @Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 2, max = 100, message = "Password must be between 2 and 100 characters")
    private String password;
}