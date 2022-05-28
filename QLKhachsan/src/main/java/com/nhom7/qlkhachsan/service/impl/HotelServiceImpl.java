package com.nhom7.qlkhachsan.service.impl;

import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.hotel.Room;
import com.nhom7.qlkhachsan.entity.rating.Follow;
import com.nhom7.qlkhachsan.entity.user.User;
import com.nhom7.qlkhachsan.repository.BookingRepository;
import com.nhom7.qlkhachsan.repository.FollowRepository;
import com.nhom7.qlkhachsan.repository.HotelRepository;
import com.nhom7.qlkhachsan.repository.RoomRepository;
import com.nhom7.qlkhachsan.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> searchHotelByName(String nameHotel) {
        return hotelRepository.findAllByNameContaining(nameHotel);
    }

    @Override
    public Hotel getHotelByName(String nameHotel) {
        return hotelRepository.findByName(nameHotel);
    }

    @Override
    public Hotel getHotelByID(Long id) {
        return hotelRepository.getById(id);
    }

    @Override
    public List<Room> getAllByHotelID(Long id) {
        return roomRepository.getAllRoomsByHotelID(id);
    }

    @Override
    public Hotel addHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    @Override
    public Follow getFollowByUserAndHotel(Long userId, Long hotelId) {
        return followRepository.findByHotelIsFollowedIdAndUserFollowId(hotelId, userId);
    }

    @Override
    public void followHotel(User user, Long hotelID) {
        Follow follow = new Follow();
        Hotel hotel = hotelRepository.getById(hotelID);
        follow.setUserFollow(user);
        follow.setHotelIsFollowed(hotel);
        followRepository.save(follow);
        System.out.println("Follow successfully");
    }

    @Override
    public void unfollowHotel(User user, Long hotelID) {
        Follow follow = followRepository.findByHotelIsFollowedIdAndUserFollowId(hotelID, user.getId());
        followRepository.delete(follow);
        System.out.println("Unfollow successfully");
    }

}
