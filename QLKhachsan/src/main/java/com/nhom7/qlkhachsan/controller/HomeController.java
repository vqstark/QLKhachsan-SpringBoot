package com.nhom7.qlkhachsan.controller;

import com.nhom7.qlkhachsan.dto.BookingDTO;
import com.nhom7.qlkhachsan.dto.SearchingDTO;
import com.nhom7.qlkhachsan.entity.hotel.BookingRoom;
import com.nhom7.qlkhachsan.entity.user.User;
import com.nhom7.qlkhachsan.repository.BookingRepository;
import com.nhom7.qlkhachsan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("search", new SearchingDTO());
        return "index";
    }



//    @GetMapping("/reservation")
//    public String loadPageReservation() {
//        return "reservation";
//    }


    @GetMapping("/rooms")
    public String loadPageRooms() {
        return "rooms";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username);
    }

}
