package com.nhom7.qlkhachsan.service.impl;

import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.user.User;
import com.nhom7.qlkhachsan.repository.HotelRepository;
import com.nhom7.qlkhachsan.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel addHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

//    @Override
//    public Set<Hotel> findHotel(User user) {
//        return hotelRepository.findAllByUser(user);
//    }


}
