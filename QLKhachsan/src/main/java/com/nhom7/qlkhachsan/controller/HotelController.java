package com.nhom7.qlkhachsan.controller;

import com.nhom7.qlkhachsan.entity.hotel.BookingRoom;
import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.hotel.Room;
import com.nhom7.qlkhachsan.entity.rating.Comment;
import com.nhom7.qlkhachsan.entity.rating.Follow;
import com.nhom7.qlkhachsan.entity.user.User;
import com.nhom7.qlkhachsan.repository.BookingRepository;
import com.nhom7.qlkhachsan.repository.FollowRepository;
import com.nhom7.qlkhachsan.repository.UserRepository;
import com.nhom7.qlkhachsan.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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



    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public String getUIHotel(@PathVariable Long id, Model model){
        Hotel hotel = hotelService.getHotelByID(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", hotelService.getAllByHotelID(hotel.getId()));

        Follow fl = hotelService.getFollowByUserAndHotel(getCurrentUser().getId(), id);

        if(fl != null)
            model.addAttribute("status", "unfollow");
        else
            model.addAttribute("status", "follow");
        return "hotel";
    }

    @GetMapping("/{id}/booking")
    public String getUIbookingRoom(@PathVariable Long id, Model model){
        Hotel hotel = hotelService.getHotelByID(id);
        List<Room> rooms = hotelService.getAllByHotelID(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", rooms);
        model.addAttribute("booking", new BookingRoom());
        return "reservation";
    }

    @PostMapping("/{id}/booking")
    public String bookingRoom(@PathVariable String name, BookingRoom bookingRoom){
        bookingRoom.setUserBook(getCurrentUser());
        bookingRepository.save(bookingRoom);
        return "bookingSuccessful";
    }

    @GetMapping("/follow/{id}")
    public String followHotel(@PathVariable Long id, Model model){
        hotelService.followHotel(getCurrentUser(), id);
        Hotel hotel = hotelService.getHotelByID(id);
        model.addAttribute("hotel", hotel);
//        model.addAttribute("rooms", hotelService.getAllByHotelID(hotel.getId()));
//        Follow fl = hotelService.getFollowByUserAndHotel(getCurrentUser().getId(), id);
        String status = "unfollow";

        model.addAttribute("status", status);
        return "hotel";
    }

    @GetMapping("/unfollow/{id}")
    public String unfollowHotel(@PathVariable Long id, Model model){
        hotelService.unfollowHotel(getCurrentUser(), id);
        Hotel hotel = hotelService.getHotelByID(id);
        model.addAttribute("hotel", hotel);
//        model.addAttribute("rooms", hotelService.getAllByHotelID(hotel.getId()));
//        Follow fl = hotelService.getFollowByUserAndHotel(getCurrentUser().getId(), id);

        String status = "follow";

        model.addAttribute("status", status);
        return "hotel";
    }

//    @PostMapping("/comment/{id}")
//    public String userComment(@PathVariable Long id){
//        Comment comment = new Comment();
//        follow.setUserFollow(getCurrentUser());
//        follow.setHotelIsFollowed(hotelService.getHotelByID(id));
//        followRepository.save(follow);
//        return "bookingSuccessful";
//    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username);
    }
}
