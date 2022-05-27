package com.nhom7.qlkhachsan.service.impl;

import com.nhom7.qlkhachsan.entity.hotel.Room;
import com.nhom7.qlkhachsan.repository.RoomRepository;
import com.nhom7.qlkhachsan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }
}
