package com.nhom7.qlkhachsan.controller;

import com.nhom7.qlkhachsan.dto.BookingDTO;
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
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loadPageLogin() {
        return "login";
    }

//    @GetMapping("/reservation")
//    public String loadPageReservation() {
//        return "reservation";
//    }

    @GetMapping("/my_reservation")
    public String LoadMyReservation(Model model) {
        List<BookingRoom> bookingRoomList = bookingRepository.findAllByUserBookId(getCurrentUser().getId());
        List<BookingDTO> bookingRoomDTOList = new ArrayList<>();
        for(BookingRoom b: bookingRoomList){
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setRoom_name(b.getRoomIsBooked().getRoomName());
            bookingDTO.setTimeBegin(b.getTimeBegin());
            bookingDTO.setTimeEnd(b.getTimeEnd());
            bookingDTO.setPrice(b.getPrice());
            bookingDTO.setPaid(false);
        }
        model.addAttribute("MyReservation", bookingRoomDTOList);
        return "myReservation";
    }

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
