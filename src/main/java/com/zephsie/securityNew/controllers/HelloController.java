package com.zephsie.securityNew.controllers;

import com.zephsie.securityNew.security.PersonDetails;
import com.zephsie.securityNew.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    private final AdminService adminService;

    @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/show_user_info")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        System.out.println(personDetails.getPerson());

        return "hello";
    }

    @GetMapping("/admin")
    public String admin() {
        adminService.doSomething();
        return "admin";
    }
}