package com.nhom7.qlkhachsan.service;

import com.nhom7.qlkhachsan.entity.hotel.Room;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoomService {
    Room addRoom(Room room);

    List<Room> getEmptyRooms(Long idHotel);
}
