package com.zephsie.securityNew.controllers;

import com.zephsie.securityNew.models.Person;
import com.zephsie.securityNew.security.PersonDetails;
import com.zephsie.securityNew.services.AdminService;
import com.zephsie.securityNew.services.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    private final AdminService adminService;

    private final DummyService dummyService;

    @Autowired
    public HelloController(AdminService adminService, DummyService dummyService) {
        this.adminService = adminService;
        this.dummyService = dummyService;
    }

    @GetMapping("/open")
    public String open() {
        try {
            return dummyService.doSomething().get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "All logged in users can see this";
    }

    @GetMapping("/show_user_info")
    public Person showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        return personDetails.getPerson();
    }

    @GetMapping("/admin")
    public String admin() {
        adminService.doSomething();
        return "Only admin can see this";
    }
}