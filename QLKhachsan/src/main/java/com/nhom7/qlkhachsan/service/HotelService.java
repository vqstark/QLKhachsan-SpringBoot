package com.nhom7.qlkhachsan.service;

import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.hotel.Room;

import java.util.List;

public interface HotelService {
    List<Hotel> getAll();

    List<Hotel> searchHotelByName(String nameHotel);

    Hotel getHotelByName(String nameHotel);

    List<Room> getAllByHotelID(Long id);
}
