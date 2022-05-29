package com.nhom7.qlkhachsan.controller;

import com.nhom7.qlkhachsan.dto.BookingDTO;
import com.nhom7.qlkhachsan.entity.hotel.BookingRoom;
import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.hotel.Room;
import com.nhom7.qlkhachsan.entity.rating.Comment;
import com.nhom7.qlkhachsan.entity.rating.Follow;
import com.nhom7.qlkhachsan.entity.user.User;
import com.nhom7.qlkhachsan.repository.*;
import com.nhom7.qlkhachsan.service.HotelService;
import com.nhom7.qlkhachsan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public String getUIHotel(@PathVariable Long id, Model model){
        Hotel hotel = hotelService.getHotelByID(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", hotelService.getSomeRoomInType(hotel.getId()));
        model.addAttribute("newComment", new Comment());
        Comment lastestComment = hotelService.getLastestComment(id);
        model.addAttribute("lastestComment", lastestComment);
        if(lastestComment==null)
            model.addAttribute("lastestComment", "no");

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
        List<Room> rooms = roomService.getEmptyRooms(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", rooms);

//        BookingRoom bookingRoom = new BookingRoom();
//        model.addAttribute("booking", bookingRoom);
        model.addAttribute("booking", new BookingDTO());
        List<String> roomName = new ArrayList<String>();

        for(Room r: rooms)
        {
            roomName.add(r.getRoomName());
        }
        model.addAttribute("roomName", roomName);
        return "reservation";
    }

    @PostMapping("/booking")
    public String bookingRoom(BookingDTO bookingDTO, Model model){
        BookingRoom bookingRoom = new BookingRoom();
        bookingRoom.setUserBook(getCurrentUser());
        Room room = roomRepository.findByRoomName(bookingDTO.getRoom_name());
        bookingRoom.setRoomIsBooked(room);
//        bookingRoom.setUserBook(getCurrentUser());
//        Hotel hotel = (Hotel)model.getAttribute("hotel");
//        String roomName = (String)model.getAttribute("roomName");
//        System.out.println(roomName);
//        bookingRoom.setRoomIsBooked(room);
        bookingRoom.setTimeBegin(bookingDTO.getTimeBegin());
        bookingRoom.setTimeEnd(bookingDTO.getTimeEnd());
        bookingRoom.setPaid(false);
        bookingRoom.setPrice(room.getPrice()*5);
        bookingRepository.save(bookingRoom);
        return "redirect:/";
    }

    @GetMapping("/follow/{id}")
    public String followHotel(@PathVariable Long id, Model model){
        hotelService.followHotel(getCurrentUser(), id);
        Hotel hotel = hotelService.getHotelByID(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("newComment", new Comment());
        Comment lastestComment = hotelService.getLastestComment(id);
        model.addAttribute("lastestComment", lastestComment);
        if(lastestComment==null)
            model.addAttribute("lastestComment", "no");

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
        model.addAttribute("newComment", new Comment());
        Comment lastestComment = hotelService.getLastestComment(id);
        model.addAttribute("lastestComment", lastestComment);
        if(lastestComment==null)
            model.addAttribute("lastestComment", "no");

//        model.addAttribute("rooms", hotelService.getAllByHotelID(hotel.getId()));
//        Follow fl = hotelService.getFollowByUserAndHotel(getCurrentUser().getId(), id);

        String status = "follow";

        model.addAttribute("status", status);
        return "hotel";
    }

    @PostMapping("/comment")
    public String commentHotel(Comment comment, Model model){
        System.out.println("Comment" + comment);
        Hotel hotel = (Hotel)model.getAttribute("hotel");
        comment.setUserComment(getCurrentUser());
        comment.setHotelIsCommented(hotel);
        commentRepository.save(comment);
        model.addAttribute("hotel", hotel);
        model.addAttribute("newComment", new Comment());
        Comment lastestComment = hotelService.getLastestComment(hotel.getId());
        model.addAttribute("lastestComment", lastestComment);
        if(lastestComment==null)
            model.addAttribute("lastestComment", "no");
        System.out.println("Comment successfully");
        return "hotel";
    }

    @GetMapping("/search")
    public String searchRoome(@RequestParam(value = "body") String body, Model model){
        Hotel hotel = hotelService.getHotelByName(body);
        model.addAttribute("hotel", hotel);
        return "searchRoom";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username);
    }
}
