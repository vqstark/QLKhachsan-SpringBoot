package com.nhom7.qlkhachsan.repository;

import com.nhom7.qlkhachsan.entity.hotel.Room;
import com.nhom7.qlkhachsan.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room save(Room room);
    //Room findById(Long id);
}
