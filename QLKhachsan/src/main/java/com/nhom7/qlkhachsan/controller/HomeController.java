package com.nhom7.qlkhachsan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/loginPage")
    public String loadPageLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String loadPageSignup() {
        return "signup";
    }

    @GetMapping("/reservation")
    public String loadPageReservation() {
        return "reservation";
    }

    @GetMapping("/rooms")
    public String loadPageRooms() {
        return "rooms";
    }
}
