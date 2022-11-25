package com.zephsie.securityNew.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @PreAuthorize("hasRole('ADMIN')")
    public void doSomething() {
        System.out.println("doing something");
    }
}