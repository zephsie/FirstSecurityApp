package com.zephsie.securityNew.controllers;

import com.zephsie.securityNew.models.Person;
import com.zephsie.securityNew.security.PersonDetails;
import com.zephsie.securityNew.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final AdminService adminService;

    @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
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