package com.zephsie.securityNew.config;

import com.zephsie.securityNew.security.PersonAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonAuthProvider personAuthProvider;

    @Autowired
    public SecurityConfig(PersonAuthProvider personAuthProvider) {
        this.personAuthProvider = personAuthProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(personAuthProvider);
    }
}