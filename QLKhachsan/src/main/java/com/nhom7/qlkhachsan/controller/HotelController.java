package com.nhom7.qlkhachsan.controller;

import com.nhom7.qlkhachsan.entity.hotel.BookingRoom;
import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.hotel.Room;
import com.nhom7.qlkhachsan.entity.user.User;
import com.nhom7.qlkhachsan.repository.BookingRepository;
import com.nhom7.qlkhachsan.repository.UserRepository;
import com.nhom7.qlkhachsan.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/{name}")
    public String getUIHotel(@PathVariable String name, Model model){
        Hotel hotel = hotelService.getHotelByName(name);
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", hotelService.getAllByHotelID(hotel.getId()));
        return "hotel";
    }

    @GetMapping("/{name}/booking")
    public String getUIbookingRoom(@PathVariable String name, Model model){
        Hotel hotel = hotelService.getHotelByName(name);
        List<Room> rooms = hotelService.getAllByHotelID(hotel.getId());
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", rooms);
        model.addAttribute("booking", new BookingRoom());
        return "reservation";
    }

    @PostMapping("/{name}/booking")
    public String bookingRoom(@PathVariable String name, BookingRoom bookingRoom){
        bookingRoom.setUserBook(getCurrentUser());
        bookingRepository.save(bookingRoom);
        return "bookingSuccessful";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username);
    }
}
