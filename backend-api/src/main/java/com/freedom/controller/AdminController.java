package com.freedom.controller;

import com.freedom.model.Admin;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping
    public Admin add() {

        Admin admin = new Admin();
        return admin;
    }

}
