package com.zephsie.securityNew.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username is required")
    @Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 2, max = 30, message = "Password must be between 2 and 30 characters")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Age is required")
    @Min(value = 1900, message = "Year must be greater than 1900")
    @Column(name = "year_of_birth")
    private Integer year;
}