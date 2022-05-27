package com.nhom7.qlkhachsan.service.impl;

import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.hotel.Room;
import com.nhom7.qlkhachsan.repository.BookingRepository;
import com.nhom7.qlkhachsan.repository.HotelRepository;
import com.nhom7.qlkhachsan.repository.RoomRepository;
import com.nhom7.qlkhachsan.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;

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
    public List<Room> getAllByHotelID(Long id) {
        return roomRepository.getAllRoomsByHotelID(id);
    }
}
