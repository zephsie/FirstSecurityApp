package com.zephsie.securityNew.services;

import com.zephsie.securityNew.util.Loggable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Loggable
    @PreAuthorize("hasRole('ADMIN')")
    public void doSomething() {
        System.out.println("doing something");
    }
}
