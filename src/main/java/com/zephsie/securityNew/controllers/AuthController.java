package com.zephsie.securityNew.controllers;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zephsie.securityNew.dto.AuthenticationDTO;
import com.zephsie.securityNew.dto.PersonDTO;
import com.zephsie.securityNew.models.Person;
import com.zephsie.securityNew.security.JwtUtil;
import com.zephsie.securityNew.services.RegisterService;
import com.zephsie.securityNew.util.PersonDTOConverter;
import com.zephsie.securityNew.util.PersonErrorResponse;
import com.zephsie.securityNew.util.PersonValidationException;
import com.zephsie.securityNew.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.StringJoiner;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegisterService registrationService;
    private final JwtUtil jwtUtil;
    private final PersonDTOConverter personDTOConverter;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(PersonValidator personValidator, RegisterService registrationService, JwtUtil jwtUtil, PersonDTOConverter personDTOConverter, AuthenticationManager authenticationManager) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.personDTOConverter = personDTOConverter;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody @Valid AuthenticationDTO authenticationDTO , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringJoiner joiner = new StringJoiner(", ");

            bindingResult.getAllErrors().forEach(objectError -> joiner.add(objectError.getDefaultMessage()));

            throw new PersonValidationException(joiner.toString());
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new PersonValidationException("Invalid username or password");
        }

        return Map.of("token", jwtUtil.generateToken(authenticationDTO.getUsername()));
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public Map<String, String> register(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        Person person = personDTOConverter.convertToEntity(personDTO);

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            StringJoiner joiner = new StringJoiner(", ");

            bindingResult.getAllErrors().forEach(objectError -> joiner.add(objectError.getDefaultMessage()));

            throw new PersonValidationException(joiner.toString());
        }

        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());

        return Map.of("token", token);
    }

    @ExceptionHandler(PersonValidationException.class)
    private ResponseEntity<PersonErrorResponse> handleException(PersonValidationException exception) {
        return ResponseEntity.badRequest().body(new PersonErrorResponse(exception.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(JsonProcessingException.class)
    private ResponseEntity<PersonErrorResponse> handleException(JsonProcessingException e) {
        return ResponseEntity.badRequest().body(new PersonErrorResponse("Invalid JSON", System.currentTimeMillis()));
    }
}