package com.nhom7.qlkhachsan.service;

import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.hotel.Room;
import com.nhom7.qlkhachsan.entity.rating.Follow;
import com.nhom7.qlkhachsan.entity.user.User;

import java.util.List;

public interface HotelService {
    List<Hotel> getAll();

    List<Hotel> searchHotelByName(String nameHotel);

    Hotel getHotelByName(String nameHotel);

    Hotel getHotelByID(Long id);
    
    List<Hotel> getHotelsByOwner(User user);

    List<Room> getAllByHotelID(Long id);

    Hotel addHotel(Hotel hotel);

    Follow getFollowByUserAndHotel(Long userId, Long hotelId);

    void followHotel(User user, Long hotelID);

    void unfollowHotel(User user, Long hotelID);
    
    void deleteById(Long id);

    void updateHotel(Hotel hotel);
}
