package com.nhom7.qlkhachsan.service.impl;

import com.nhom7.qlkhachsan.dto.UserBookingDTO;
import com.nhom7.qlkhachsan.repository.BookingRoomRepository;
import com.nhom7.qlkhachsan.service.BookingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingRoomServiceImpl implements BookingRoomService {

    @Autowired
    BookingRoomRepository bookingRoomRepository;


    @Override
    public List<UserBookingDTO> getAll() {
        return bookingRoomRepository.getListUserBooked();
    }


}
