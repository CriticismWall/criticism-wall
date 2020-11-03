package com.freedom.controller;

import com.freedom.model.Admin;
import com.freedom.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public Admin add() {
        Admin admin = adminService.create();
        adminService.findTopBy();

        return admin;
    }



}
