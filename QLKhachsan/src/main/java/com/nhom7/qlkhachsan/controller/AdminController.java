package com.nhom7.qlkhachsan.controller;

import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.hotel.Room;
import com.nhom7.qlkhachsan.repository.HotelRepository;
import com.nhom7.qlkhachsan.service.HotelService;
import com.nhom7.qlkhachsan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private HotelRepository hotelRepository;
    @GetMapping
    public String home() {
        return "/admin/index";
    }

    @GetMapping("addRoom")
    public String loadPageAddRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hotels", hotelRepository.findAll());
        return "/admin/rooms/add_room";
    }
    @GetMapping("addHotel")
    public String loadPageAddHotel(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "/admin/rooms/addHotel";
    }

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;

    @PostMapping("/addHotel")
    public String addHotel(Hotel hotel){
        //System.out.println(hotel.toString());
        hotelService.addHotel(hotel);
        return "redirect:/admin";
    }

    @PostMapping("/addRoom")
    public String addRoom(Room room){
        System.out.println(room.toString());
        room.setStatus(true);
        //room.setHotelHasRooms(hotel);
        roomService.addRoom(room);
        return "redirect:/admin";
    }
}
